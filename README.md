# FILE WEB SERVER - API REST (TALLER 2 AREP)

Taller 2 del curso de Arquitecturas Empresariales (AREP) en el que se implementa un servidor HTTP el cual es capaz de leer y retornar archivos contenidos en el disco duro del servidor. Además, implementa una galeria interactiva de imagenes las cuales consiguen sus datos mediante un servicio API REST.

## Diseño
Este proyecto funciona y esta organizado en diferentes componentes con responsabilidades unicas.

1. `HTTPResponseData` es el componente encargado de leer y responder a los clientes con los datos relacionados a archivos dentro del servidor, tal como documentos HTML e imagenes.
2. `HTTPResponseHeaders` componente encargado de generar los respectivos encabezados de respuesta HTTP para las diferentes peticiones de los clientes.
3. `HTTPServer` componente principal del proyecto encargado de administrar el servicio HTTP y de atender las solicitudes de conexión de los clientes. Además, administra un sistema API que consume el front.
4. `Frontend` en este proyecto se cuenta con una galeria de imagenes con las cuales el cliente puede interactuar como si fuese una pasarela. Implementa un metodo REST asincrono para llamar al servidor HTTP y obtener los recursos multimedia alojados en este.
5. `Almacenamiento del servidor` para este proyecto se solicito exclusivamente que los datos solicitados por el cliente web y el usuario estuviesen almacenados en el disco del servidor, para ello, se tiene la carpeta `web-files` la cual contiene todos estos archivos de prueba para comprobar que se esta consultando, leyendo y enviando el contenido de este directorio alojado en el servidor.

## Extensión e implementación de otros servicios

Aún existen algunas funcionalidades a tener en cuenta para futuras versiones de este proyecto:

1. El componente encargado de generar encabezados puede extenderse para agregar aún más códigos de respuesta a diferencia de los que se encuentran actualmente (200 y 404) esto con el fin de cubrir todos los espectros de prueba que el usuario pueda generar.

2. El factor de lectura y envio de datos del servidor es perfectamente funcional, la página web podría aprovechar esta ventaja para implementar aún más recursos multimedia, tal como video y demás elementos audiovisuales.

3. Podría implementarse un servicio de subida de imagenes para alimentar la galeria con más datos ofreciendo más posibilidades de consumo dentro del proyecto.

## Instrucciones de uso

### Pre-requisitos

Antes de ejecutar el servidor es necesario contar con los siguientes programas instalados y funcionando:

1. JDK (Java Development Kit)
2. MVN (Maven)

### Instalación y Ejecución

A continuación se muestra el paso a paso de como instalar y ejecutar el servidor HTTP

1. Clone este repositorio localmente en un entorno o carpeta de trabajo.

```
$ git clone https://github.com/NickArB/AREP-TALLER-2.git
```

2. Dentro del entorno o directorio en el que clono el proyecto, asegurese de que no existan ejecutables previos o no deseados con maven.

```
$ mvn clean
```
3. Una vez que los targets han sido descartados compilelos y re asignelos al target.
```
$ mvn package
```
4. Con los target asignados, ejecute el metodo main de la clase HTTPServer. Dependiendo de su IDE esta clase se puede ejecutar de varias formas, en caso de no tener un IDE se recomienda el uso del siguiente comando
```
$ java '-XX:+ShowCodeDetailsInExceptionMessages' -cp '<Path-al-directorio-de-trabajo>\target\classes' 'edu.escuelaing.arep.app.HTTPServer'
```
5. Una vez el servicio esta corriendo puede verificar que esta funcionando al escribir la ruta en el navegador
```
http://localhost:35000/index.html
```
A continuación se muestra la interfaz de usuario en el browser junto a un boton con el cual se cambia de imagen a imagen hasta completar la galeria.

![Sample Image 1](images/sample1.png)
![Sample Image 2](images/sample2.png)
![Sample Image 3](images/sample3.png)


## Ejecutando pruebas unitarias

A continuación se presenta como ejecutar las pruebas unitarias
1. Ubiquese en el directorio o entorno de trabajo desde su terminal.
```
$ cd <Path-al-directorio-de-trabajo>
```
2. Utilice maven para ejecutar las pruebas.
```
$ mvn test
```
3. Maven automáticamente detectara y ejecutara todas las pruebas unitarias. Debería aparecer algo similar a esto:
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.escuelaing.app.HTTPResponseDataTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.029 s -- in edu.escuelaing.app.HTTPResponseDataTest
[INFO] Running edu.escuelaing.app.HTTPResponseHeadersTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s -- in edu.escuelaing.app.HTTPResponseHeadersTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.019 s
[INFO] Finished at: 2024-01-28T16:27:49-05:00
[INFO] ------------------------------------------------------------------------
``` 

## Construido con

* [Maven](https://maven.apache.org/) - Manejo de dependencias

## Version 1.0

## Autor

* **Nicolás Ariza Barbosa**

<!-- javadoc -d doc -sourcepath src/main/java -subpackages edu.escuelaing.app.taller -->
