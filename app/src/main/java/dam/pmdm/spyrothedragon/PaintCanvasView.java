package dam.pmdm.spyrothedragon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PaintCanvasView extends View {

    private ArrayList<Particle> particles;
    private Paint paint;
    private Random random;
    private int width, height;

    public PaintCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        particles = new ArrayList<>();
        random = new Random();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    // Clase interna para las partículas del fuego
    private class Particle {
        float x, y;
        float speedX, speedY;
        int color;
        float size;
        long lifetime;

        Particle(float x, float y) {
            this.x = x;
            this.y = y;
            this.size = random.nextFloat() * 5 + 5; // Tamaño aleatorio
            this.speedX = random.nextFloat() * 2 - 1; // Velocidad aleatoria en X
            this.speedY = random.nextFloat() * 2 + 1; // Velocidad en Y (hacia abajo)
            this.color = getRandomColor(); // Color aleatorio
            this.lifetime = System.currentTimeMillis();
        }

        // Cambia el color de la partícula con el tiempo para simular fuego
        public void update() {
            y += speedY; // Mover hacia abajo
            x += speedX; // Mover aleatoriamente en X

            // Cambiar color con el tiempo (enfriarse de rojo a amarillo)
            long age = System.currentTimeMillis() - lifetime;
            if (age < 1000) {
                color = getRandomColor(); // Color aleatorio al principio
            } else {
                color = Color.rgb(255, (int) Math.max(0, 255 - age / 5), 0); // Amarillo a rojo
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();

        // Crear nuevas partículas
        if (random.nextInt(5) == 0) { // Crear una partícula aleatoriamente
            // Generar partículas desde la parte superior
            particles.add(new Particle(280,500)); // X aleatorio, Y en la parte superior
        }

        // Dibujar las partículas y actualizarlas
        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.update(); // Actualizar posición y color de la partícula

            paint.setColor(p.color);
            canvas.drawCircle(p.x, p.y, p.size, paint); // Dibujar partícula

            // Eliminar partículas que hayan desaparecido (cuando están fuera de la pantalla)
            if (p.y > height || p.size <= 0) {
                particles.remove(i);
                i--;
            }
        }

        invalidate(); // Redibujar continuamente
    }

    // Método para obtener un color aleatorio
    private int getRandomColor() {
        // Generar colores con tonos anaranjados y rojos para simular fuego
        int red = random.nextInt(255);
        int green = random.nextInt(150);
        int blue = 0;
        return Color.rgb(red, green, blue);
    }
}

