# Introducción:
Nuestra aplicación se encarga de **mostrar personajes, mundos y coleccionables de universo Spyro** aunque lo más destacado es la incorporación de una guía de uso cuyo recorrido debemos completar para evitar que se muestre en los sucesivos usos de la App.   

# Características principales:
La guía nos va mostrando cada uno de los botones de la barra de navegación inferior, como también nos explica el uso de menú información que aparece en la Toolbar superior. Cada elemento mostrado aparece con una animación, como también ocurre en el caso de las transiciones de los pasos (steps). **Para dichas animaciones se han utilizado los efectos fade y  desplazamientos**. Otra característica es la **reproducción de sonido al pulsar el botón de avance en los steps**.
Una vez completados todos los pasos de la guía, la aplicación recuerda que ya se ha visionado, y no vuelve a mostrarla.
También **tenemos tres menús ocultos**, que nos muestran un video temático, la animación de un personaje y la reactivación de la guía respectivamente.

# Tecnologías utilizadas:
La autenticación ha partido de tres fragmentos dados con un RecyclerView en cada uno de ellos que corresponden con cada uno de los item del menú de navegación inferior. **Sobre cada fragmento hemos incluido un layout** con el contenido a informar, jugando con la visibilidad del mismo y las animaciones, además de incluir un bocadillo con forma elíptica y una flecha declarados como recursos shape. Para la reproducción del video hemos instanciado un **objeto de la clase Mediaplayer**, y para la animación del personaje se ha trabajado con **la clase Canvas**. Para hacer persistente el recordatorio de haber completado la guía, se ha usado **SharedPreferences**.

# Instrucciones de uso:
Al iniciar la App mientras no se haya completado previamente, se muestra la guía en cuyo **primer step es una introducción** con un único botón para comenzar el recorrido.
En **los siguientes tres steps vemos como se destacan los tres ítems del menú inferior** y únicamente tenemos dos botones habilitados que nos permiten avanzar, o abandonar la guía.
**El quinto step nos explica el botón información** de la Toolbar superior, y además de los botones anteriores, aquí se nos habilita el botón que se está explicando para dar la posibilidad de hacer clic y poder ver su contenido.
El sexto y **último step nos relaciona los pasos recorridos** y nos da la única opción de cerrar la guía, permitiéndonos la navegación por la aplicación una vez desbloqueados todos los botones y desplazamientos.
Como hemos comentado anteriormente, **disponemos de tres menús ocultos** que se encuentran en:
  1.- **Coleccionable "Gemas"**. Haciendo 4 clics sobre él nos abre un video a pantalla completa que desaparece al finalizar o al abortar con el botón salir.
  2.- **Personaje "Spyro"**. En este caso, si hacemos sobre él una pulsación larga, se mostrará una animación consistente en simular la salida de fuego de su boca.
  3.- **Mundo "Destello"**. Un único clic sobre él hace que se olvide el recuerdo de haber completado la guía, mostrándose de nuevo la próxima vez que se visite la aplicación.
  
# Conclusiones del desarrollador: Reflexiona sobre el proceso de desarrollo y cualquier desafío o aprendizaje obtenido durante el proyecto.
Considero que la elaboración de una guía es muy importante ya que aunque el desarrollador tenga muy claro el uso de la misma, puede no resultar intuitiva para el resto de usuarios. En cuanto a la experiencia personal, destacar que para la elaboración he necesitado invertir muchas horas, pero han pasado rápidamente al ir en aumento el entusiasmo por descubrir como era capaz de darle forma a los contenidos solicitados, a la vez que descubrir como cada vez más nuestras aplicaciones se van pareciendo, en cuanto a recursos puestos a disposición del usuario, a las disponibles en el mundo real.

# Conclusiones del desarrollador: Reflexiona sobre el proceso de desarrollo y cualquier desafío o aprendizaje obtenido durante el proyecto.
Considero que la elaboración de una guía es muy importante ya que aunque el desarrollador tenga muy claro el uso de la misma, puede no resultar intuitiva para el resto de usuarios. En cuanto a la experiencia personal, destacar que para la elaboración he necesitado invertir muchas horas, pero han pasado rápidamente al ir en aumento el entusiasmo por descubrir como era capaz de darle forma a los contenidos solicitados, a la vez que descubrir como cada vez más nuestras aplicaciones se van pareciendo, en cuanto a recursos puestos a disposición del usuario, a las disponibles en el mundo real.

# Capturas de pantalla:
![image](https://github.com/user-attachments/assets/0f8c8a00-d9db-4cb8-9374-d9f111382df3)
![image](https://github.com/user-attachments/assets/4088dd30-45f7-408c-a6fd-4df65be1cff6)
![image](https://github.com/user-attachments/assets/0adcf271-2c2d-412e-8592-25c564bcf6ad)
![image](https://github.com/user-attachments/assets/65bffb85-9cdf-4205-b8f7-eb491b3e0bd0)
![image](https://github.com/user-attachments/assets/53a9cd6b-5d3a-4262-9560-855316c7eddd)

# Video demostración:
Se puede ver una demostración del funcionamiento en el siguiente enlace:
https://drive.google.com/file/d/1K9FOTjZiJrh8nXrMdUg7dxMwe12nGLNg/view?usp=drive_link




