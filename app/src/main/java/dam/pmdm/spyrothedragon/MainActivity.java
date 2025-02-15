package dam.pmdm.spyrothedragon;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import dam.pmdm.spyrothedragon.databinding.ActivityMainBinding;
import dam.pmdm.spyrothedragon.databinding.FireBinding;
import dam.pmdm.spyrothedragon.databinding.Guide1Binding;
import dam.pmdm.spyrothedragon.databinding.Guide2Binding;
import dam.pmdm.spyrothedragon.databinding.Guide3Binding;
import dam.pmdm.spyrothedragon.databinding.Guide4Binding;
import dam.pmdm.spyrothedragon.databinding.Guide5Binding;
import dam.pmdm.spyrothedragon.databinding.Guide6Binding;
import dam.pmdm.spyrothedragon.databinding.VideoBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController = null;
    boolean needToStartGuide = true;
    private boolean guideStep2 = false;
    private boolean guideStep3 = false;
    private boolean guideStep4 = false;
    private boolean guideStep5 = false;
    private boolean guideStep6 = false;
    private Guide1Binding guide1Binding;
    private Guide2Binding guide2Binding;
    private Guide3Binding guide3Binding;
    private Guide4Binding guide4Binding;
    private Guide5Binding guide5Binding;
    private Guide6Binding guide6Binding;
    private VideoBinding videoBinding;
    private FireBinding fireBinding;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        guide1Binding = binding.include1Layout;
        guide2Binding = binding.include2Layout;
        guide3Binding = binding.include3Layout;
        guide4Binding = binding.include4Layout;
        guide5Binding = binding.include5Layout;
        guide6Binding = binding.include6Layout;
        videoBinding = binding.includeVideo;
        fireBinding = binding.includeFire;

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            NavigationUI.setupWithNavController(binding.navView, navController);
            NavigationUI.setupWithNavController(binding.toolbar, navController);
        }
        // Configurar Toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navView.setOnItemSelectedListener(this::selectedBottomMenu);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.navigation_characters ||
                    destination.getId() == R.id.navigation_worlds ||
                    destination.getId() == R.id.navigation_collectibles) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });

        // Controlamos si hay que ejecutar la guia o ya se ha hecho.
        SharedPreferences sharedPreferences = this.getSharedPreferences("mi_prefs", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        needToStartGuide=sharedPreferences.getBoolean("needToStartGuide", true);
        if (needToStartGuide) {
            initializedGuide();
        }
    }


    private boolean selectedBottomMenu(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_characters)
            navController.navigate(R.id.navigation_characters);
        else if (menuItem.getItemId() == R.id.nav_worlds)
            navController.navigate(R.id.navigation_worlds);
        else
            navController.navigate(R.id.navigation_collectibles);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gestiona el clic en el ítem de información
        if (item.getItemId() == R.id.action_info) {
            showInfoDialog();  // Muestra el diálogo
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInfoDialog() {
        // Crear un diálogo de información
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_about)
                .setMessage(R.string.text_about)
                .setPositiveButton(R.string.accept, null)
                .show();
    }

    /**
     * Metodo que da comiezo a la guía.
     */
    private void initializedGuide() {
        if (needToStartGuide) {
            guide1Binding.guideLayout1.setVisibility(View.VISIBLE);
            guide1Binding.buttonStartGuide.setOnClickListener(this::nextStepGuide);
            soundTransition();
        }
    }

    /**
     * Metodo que da paso al siguiente Step de la guia.
     *
     * @param view
     */
    private void nextStepGuide(View view) {
        //sonido al cargar cada step;
        soundTransition();

        if (!guideStep2) {
            //bloqueamos futuro acceso a este Step
            guideStep2 = true;
            //llamamos a la transicion del Step actual al siguiente
            showStepHorizontal(guide1Binding.guideLayout1, guide2Binding.guideLayout2);
            //posicionamos el pulseImage en el primer botón de la botonNavigation.
            View botonPersonajes = binding.navView.findViewById(R.id.nav_characters);
            guide2Binding.pulseImage.setX(botonPersonajes.getX() - 60);
            //animamos elementos Step.
            animacionGuide(guide2Binding.pulseImage, guide2Binding.flecha, guide2Binding.bocadilloImage, guide2Binding.bocadilloText, null);
            //esperamos interaccion de usuario
            guide2Binding.nextStep.setOnClickListener(this::nextStepGuide);
            guide2Binding.exitGuide.setOnClickListener(this::onExitGuide);

        } else if (!guideStep3) {
            guideStep3 = true;
            showStepVertical(guide2Binding.guideLayout2, guide3Binding.guideLayout3);
            binding.navView.setSelectedItemId(R.id.nav_worlds);
            View botonMundos = binding.navView.findViewById(R.id.nav_worlds);
            guide3Binding.pulseImage.setX(botonMundos.getX() - 60);
            animacionGuide(guide3Binding.pulseImage, guide3Binding.flecha, guide3Binding.bocadilloImage, guide3Binding.bocadilloText, null);
            guide3Binding.nextStep.setOnClickListener(this::nextStepGuide);
            guide3Binding.exitGuide.setOnClickListener(this::onExitGuide);

        } else if (!guideStep4) {
            guideStep4 = true;
            showStepVertical(guide3Binding.guideLayout3, guide4Binding.guideLayout4);
            binding.navView.setSelectedItemId(R.id.nav_collectibles);
            View botonColeccionables = binding.navView.findViewById(R.id.nav_collectibles);
            guide4Binding.pulseImage.setX(botonColeccionables.getX() - 60);
            animacionGuide(guide4Binding.pulseImage, guide4Binding.flecha, guide4Binding.bocadilloImage, guide4Binding.bocadilloText, null);
            guide4Binding.nextStep.setOnClickListener(this::nextStepGuide);
            guide4Binding.exitGuide.setOnClickListener(this::onExitGuide);

        } else if (!guideStep5) {
            guideStep5 = true;
            showStepVertical(guide4Binding.guideLayout4, guide5Binding.guideLayout5);
            animacionGuide(guide5Binding.pulseImage, guide5Binding.flecha, guide5Binding.bocadilloImage, guide5Binding.bocadilloText, guide5Binding.bocadilloText2);
            guide5Binding.nextStep.setOnClickListener(this::nextStepGuide);
            guide5Binding.exitGuide.setOnClickListener(this::onExitGuide);
            guide5Binding.infoButton.setOnClickListener(v -> showInfoDialog());

        } else if (!guideStep6) {
            guideStep6 = true;
            activateGuide(false);
            showStepHorizontal(guide5Binding.guideLayout5, guide6Binding.guideLayout6);
            guide6Binding.exitGuide.setOnClickListener(this::onExitGuide);
        }
    }

    /**
     * Metodo que anima los distintos elementos de un Step.
     *
     * @param pulseImage
     * @param flecha
     * @param bocadilloImage
     * @param bocadilloText
     */
    private void animacionGuide(ImageView pulseImage, ImageView flecha, ImageView bocadilloImage, TextView bocadilloText, TextView bocadilloText2) {
        ObjectAnimator scaleXpulse = ObjectAnimator.ofFloat(
                pulseImage, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYpulse = ObjectAnimator.ofFloat(
                pulseImage, "scaleY", 0.5f, 1f);
        ObjectAnimator scaleXflecha = ObjectAnimator.ofFloat(
                flecha, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleXbocadillo = ObjectAnimator.ofFloat(
                bocadilloImage, "scaleX", 0f, 1f);
        ObjectAnimator scaleYbocadillo = ObjectAnimator.ofFloat(
                bocadilloImage, "scaleY", 0f, 1f);
        ObjectAnimator fadeinTexto = ObjectAnimator.ofFloat(
                bocadilloText, "alpha", 0f, 1f);
        ObjectAnimator fadeinTexto2 = ObjectAnimator.ofFloat(
                bocadilloText2, "alpha", 0f, 1f);

        AnimatorSet scalePulse = new AnimatorSet();
        scalePulse.playTogether(scaleXpulse, scaleYpulse, fadeinTexto2);

        AnimatorSet scaleBocadillo = new AnimatorSet();
        scaleBocadillo.playTogether(scaleXbocadillo, scaleYbocadillo);

        scaleXpulse.setRepeatCount(2);
        scaleYpulse.setRepeatCount(2);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(fadeinTexto).after(scaleBocadillo).before(scaleXflecha).before(scalePulse);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * Metodo para la transision de Steps.
     *
     * @param LayoutOut Layout saliente
     * @param LayoutIn  Layout entrante
     */
    private void showStepVertical(FrameLayout LayoutOut, FrameLayout LayoutIn) {
        //Prepara el proximo Step
        LayoutIn.setTranslationY(LayoutIn.getHeight());
        LayoutIn.setVisibility(View.VISIBLE);
        LayoutIn.animate().translationY(0).setDuration(1000);

        //Transición de un Step a otro
        LayoutOut.animate()
                .translationY(LayoutOut.getHeight())
                .setDuration(1000)
                .withEndAction(() -> LayoutOut.setVisibility(View.GONE));
    }

    private void showStepHorizontal(FrameLayout LayoutOut, FrameLayout LayoutIn) {
        //Prepara el proximo Step
        LayoutIn.setTranslationX(LayoutIn.getWidth());
        LayoutIn.setVisibility(View.VISIBLE);
        LayoutIn.animate().translationX(0).setDuration(300);

        //Transición de un Step a otro
        LayoutOut.animate()
                .translationX(-LayoutOut.getWidth())
                .setDuration(300)
                .withEndAction(() -> LayoutOut.setVisibility(View.GONE));
    }

    /**
     * Metodo para salir de la guia.
     *
     * @param view
     */
    private void onExitGuide(View view) {
        needToStartGuide = false;
        guide2Binding.guideLayout2.setVisibility(View.GONE);
        guide3Binding.guideLayout3.setVisibility(View.GONE);
        guide4Binding.guideLayout4.setVisibility(View.GONE);
        guide5Binding.guideLayout5.setVisibility(View.GONE);
        guide6Binding.guideLayout6.setVisibility(View.GONE);
    }

    private void soundTransition() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audio_gema);
        mediaPlayer.start();

        // Establecer el listener para cuando la reproducción termine
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Liberar el MediaPlayer cuando la reproducción termine
                mp.release();
            }
        });
    }

    /**
     * Metodo que reproduce el video del Easter Egg
     */
    public void videoEasterEgg1() {
        videoBinding.layoutVideo.setVisibility(View.VISIBLE);
        videoBinding.videoView.setVideoPath("android.resource://"
                + this.getPackageName() + "/"
                + R.raw.mivideo);
        videoBinding.videoView.start();
        // Cuando se pulsa el boton saltar
        videoBinding.exitVideo.setOnClickListener(mp -> {
            videoBinding.videoView.stopPlayback();
            videoBinding.layoutVideo.setVisibility(View.GONE);
        });
        // Cuando el video ha terminado de reproducirse
        videoBinding.videoView.setOnCompletionListener(mp -> {
            videoBinding.layoutVideo.setVisibility(View.GONE);
        });
    }

    public void openCanvas() {
        fireBinding.layoutFire.setVisibility(View.VISIBLE);
        fireBinding.exit.setOnClickListener(v -> {
            fireBinding.layoutFire.setVisibility(View.GONE);
        });
    }

    public void activateGuide(boolean valor){
        SharedPreferences sharedPreferences = this.getSharedPreferences("mi_prefs", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.needtostartguide), valor);
        editor.apply();
        if (valor){
            Toast.makeText(this, R.string.guia_texto_activada, Toast.LENGTH_SHORT).show();
        }

    }
}