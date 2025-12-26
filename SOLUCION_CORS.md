# 🔧 SOLUCIÓN RÁPIDA - Error CORS

## Problema Detectado
El error `No 'Access-Control-Allow-Origin' header is present` indica que el API Gateway no está agregando los headers CORS a las respuestas.

## ✅ Cambios Realizados

### 1. **Configuración en application.properties**
Se agregó configuración CORS global en `api-gateway/src/main/resources/application.properties`:

```properties
# --- CONFIGURACIÓN CORS ---
spring.cloud.gateway.globalcors.cors-configurations.[/**].allowed-origins=*
spring.cloud.gateway.globalcors.cors-configurations.[/**].allowed-methods=GET,POST,PUT,DELETE,OPTIONS,PATCH
spring.cloud.gateway.globalcors.cors-configurations.[/**].allowed-headers=*
spring.cloud.gateway.globalcors.cors-configurations.[/**].allow-credentials=true
spring.cloud.gateway.globalcors.cors-configurations.[/**].max-age=3600
```

### 2. **Clase CorsConfig.java actualizada**
Se mejoró la clase con `setAllowedOriginPatterns` para compatibilidad con `allowCredentials(true)`.

## 🚀 PASOS PARA APLICAR LA SOLUCIÓN

### Paso 1: Detener el API Gateway
En IntelliJ IDEA:
1. Ve a la pestaña de **Run** (parte inferior)
2. Busca el proceso **ApiGatewayApplication**
3. Haz clic en el botón **Stop** (cuadrado rojo) ⏹️

### Paso 2: Limpiar el proyecto (Opcional pero recomendado)
Ejecuta en la terminal de IntelliJ o cmd:

```bash
cd api-gateway
mvnw clean
```

O desde el proyecto completo:
```bash
cd C:\Users\USUARIO\Downloads\ecommerce-spring\api-gateway
mvnw clean
```

### Paso 3: Reiniciar el API Gateway
En IntelliJ IDEA:
1. Abre el archivo `ApiGatewayApplication.java`
2. Haz clic derecho en el archivo
3. Selecciona **Run 'ApiGatewayApplication'**

O usa el botón de **▶️ Run** en la parte superior.

### Paso 4: Verificar que el Gateway inició correctamente
Revisa los logs en la consola de IntelliJ. Debes ver algo como:

```
Netty started on port 8080
Started ApiGatewayApplication in X.XXX seconds
```

### Paso 5: Probar desde el navegador
Abre la consola del navegador (F12) y ejecuta:

```javascript
fetch('http://localhost:8080/api/productos', {
    method: 'GET',
    credentials: 'include'
})
.then(res => {
    console.log('✅ Headers de respuesta:', res.headers);
    return res.json();
})
.then(data => console.log('✅ Datos:', data))
.catch(err => console.error('❌ Error:', err));
```

## 🔍 ¿Qué deberías ver en los headers?

Después de aplicar la solución, cuando hagas una petición, el navegador debería recibir estos headers en la respuesta:

```
Access-Control-Allow-Origin: http://localhost:4300
Access-Control-Allow-Methods: GET,POST,PUT,DELETE,OPTIONS,PATCH,HEAD
Access-Control-Allow-Headers: *
Access-Control-Allow-Credentials: true
Access-Control-Max-Age: 3600
```

## ❌ Si sigue sin funcionar

### Opción A: Verificar en IntelliJ que los cambios se guardaron
1. Abre `api-gateway/src/main/resources/application.properties`
2. Verifica que al final del archivo estén las líneas de configuración CORS
3. Abre `api-gateway/src/main/java/com/example/apigateway/config/CorsConfig.java`
4. Verifica que use `setAllowedOriginPatterns(List.of("*"))`

### Opción B: Recompilar desde Maven
```bash
cd C:\Users\USUARIO\Downloads\ecommerce-spring\api-gateway
mvnw clean install
```

Luego reinicia el API Gateway desde IntelliJ.

### Opción C: Agregar logs para debug
Agrega esta línea en `application.properties`:

```properties
logging.level.org.springframework.cloud.gateway=DEBUG
logging.level.org.springframework.web.cors=DEBUG
```

Reinicia y revisa los logs para ver si CORS se está aplicando.

### Opción D: Probar sin credenciales primero
Si aún falla, temporalmente cambia en el frontend:

```javascript
// Intenta sin credentials primero
fetch('http://localhost:8080/api/productos', {
    method: 'GET'
    // Sin credentials: 'include'
})
```

Si esto funciona, el problema está en la combinación de `allowCredentials + origin pattern`.

## 📊 Arquitectura Actual

```
Angular Frontend :4300
        ↓
     [CORS]
        ↓
API Gateway :8080  ← CORS configurado aquí (2 métodos: properties + Java)
        ↓
    Eureka :8761
        ↓
Microservicios (Producto, Inventario, Órdenes)
```

## ✨ Configuración Doble (Redundancia Intencional)

Hemos configurado CORS de **DOS formas** para asegurar que funcione:

1. **application.properties**: Configuración declarativa (más simple)
2. **CorsConfig.java**: Configuración programática (más control)

Spring Cloud Gateway usará cualquiera de las dos. Esto asegura compatibilidad con diferentes versiones.

## 🎯 Próximo Paso

**IMPORTANTE**: Después de reiniciar el API Gateway, prueba nuevamente tu aplicación Angular en `http://localhost:4300`.

Si el error persiste:
1. Copia TODOS los logs del API Gateway cuando se inicia
2. Copia el error completo del navegador (incluyendo los headers de la petición)
3. Compártelos para diagnosticar el problema específico
