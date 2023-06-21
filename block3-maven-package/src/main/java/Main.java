public class Main {
    public static void main(String[] args) {
        System.out.println("¡Hola, Mundo!");
    }
}

/* Al compilar con maven se crea un archivo .jar en la carpeta target
En el archivo pom.xml incluir el siguiente plugin
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>Aquí va el package donde está la clase main (en este caso es Main unicamente)</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

Para ejecutar este programa desde la consola tenemos que estar dentro del directorio C:\Users\alvaro.fbarjau\IdeaProjects\block3-maven-package\target
Después desde ese directorio en la consola utilizar el comando java -jar "block3-maven-package-1.0-SNAPSHOT.jar"
 */