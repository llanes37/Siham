/******************************************************************************************
 * 🔐 UD6 - TAREA 5: CLASE UD6_Password Y PASSWORDAMPLIADA
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Implementar cifrado simple usando XOR y gestión de contraseñas
 *              con la palabra clave `final` y métodos sobrescritos.
 *
 * 📝 ENUNCIADO:
 * Crea la clase `UD6_Password` con:
 *  1) Un atributo `final int CLAVE = 67294358`.
 *  2) Un atributo `int password` para almacenar la contraseña cifrada.
 *  3) Constructor que recibe la contraseña original y la cifra.
 *  4) Método `encriptarDesencriptar(int valorClave)` que aplica XOR.
 *  5) Método `guardarContraseña()` que muestra la contraseña cifrada.
 *  6) Método `iniciarSesion(int claveSinCifrar)` que cifra y compara.
 *
 * Crea la subclase `PasswordAmpliada` que añade:
 *  - Atributo `int claveDesencriptada`.
 *  - Sobrescribe `guardarContraseña()` para mostrar la contraseña en claro.
 *
 * Añade el método `main` dentro de `UD6_Password` para probar ambas clases.
 ******************************************************************************************/

 public class UD6_Password {
    // ✔ CLAVE constante e inmutable
    private final int CLAVE = 67294358;
    // ❓ Contraseña cifrada (resultante del XOR)
    private int password;

    /**
     * Constructor: cifra la contraseña recibida.
     * @param claveSinCifrar Contraseña en texto claro
     */
    public UD6_Password(int claveSinCifrar) {
        this.password = encriptarDesencriptar(claveSinCifrar);
    }

    /**
     * Cifra o descifra usando XOR con CLAVE.
     * @param valorClave Contraseña en claro o cifrada
     * @return valorClave ^ CLAVE
     */
    public int encriptarDesencriptar(int valorClave) {
        return valorClave ^ CLAVE;
    }

    /**
     * Muestra la contraseña cifrada.
     */
    public void guardarContraseña() {
        System.out.println("La contraseña se ha guardado como " + password);
    }

    /**
     * Inicia sesión comprobando la contraseña cifrada.
     * @param claveSinCifrar Contraseña en texto claro
     * @return true si coincide, false si no
     */
    public boolean iniciarSesion(int claveSinCifrar) {
        int cifrada = encriptarDesencriptar(claveSinCifrar);
        if (cifrada == this.password) {
            System.out.println("Bienvenido");
            return true;
        } else {
            System.out.println("Error al iniciar sesión. Contraseña incorrecta.");
            return false;
        }
    }

    // ==================================================================
    // 🚀 MÉTODO MAIN – prueba UD6_Password y PasswordAmpliada
    // ==================================================================
    public static void main(String[] args) {
        // 🔒 Prueba de UD6_Password
        UD6_Password pwd = new UD6_Password(1234);
        pwd.guardarContraseña();       // Muestra cifrada
        pwd.iniciarSesion(1111);       // Fallo
        pwd.iniciarSesion(1234);       // Éxito

        // 🛡️ Prueba de PasswordAmpliada
        PasswordAmpliada pwd2 = new PasswordAmpliada(5678);
        pwd2.guardarContraseña();      // Muestra en claro
        pwd2.iniciarSesion(5678);      // Éxito (usa lógica del padre)
    }
}

// ========================================================================================
// 🛡️ SUBCLASE PasswordAmpliada: muestra la contraseña en claro
// ========================================================================================
class PasswordAmpliada extends UD6_Password {
    // ✅ Clave original en claro
    private int claveDesencriptada;

    /**
     * Constructor: cifra y almacena la contraseña en claro.
     * @param claveSinCifrar Contraseña en texto claro
     */
    public PasswordAmpliada(int claveSinCifrar) {
        super(claveSinCifrar);
        this.claveDesencriptada = claveSinCifrar;
    }

    /**
     * Sobrescribe guardarContraseña para mostrar clave en claro.
     * Nota: rompe encapsulación si no se controla.
     */
    @Override
    public void guardarContraseña() {
        System.out.println("(Ampliada) La contraseña en claro es " + claveDesencriptada);
    }
}

/*
 =======================================================================================
 PREGUNTAS:
 1. Atributos final:
    - Sólo CLAVE debe ser final porque es constante. `password` varía.
 2. Visibilidad de métodos:
    - `encriptarDesencriptar` podría ser protected o private con API pública.
      Métodos esenciales son públicos.
 3. Sobrescritura de guardarContraseña:
    - Puede marcarse como final en UD6_Password para evitarlo, o cambiar diseño.
 =======================================================================================
*/
