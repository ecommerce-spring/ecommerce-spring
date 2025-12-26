# Configuración CORS - Microservicios E-commerce

## ¿Qué es CORS?
CORS (Cross-Origin Resource Sharing) es un mecanismo de seguridad que permite que recursos restringidos en una página web sean solicitados desde un dominio diferente al dominio que sirvió el primer recurso.

## Configuración Implementada

### 🔒 API Gateway (Puerto 8080)
**Archivo:** `api-gateway/src/main/java/com/example/apigateway/config/CorsConfig.java`

El API Gateway usa **CorsWebFilter** (reactivo) porque Spring Cloud Gateway está basado en WebFlux.

**Configuración actual:**
- ✅ Permite todos los orígenes (`*`)
- ✅ Métodos HTTP: GET, POST, PUT, DELETE, OPTIONS, PATCH
- ✅ Todos los headers permitidos
- ✅ Credenciales habilitadas (cookies, headers de autorización)
- ✅ Caché preflight: 1 hora (3600 segundos)

### 🔧 Microservicios (Producto, Inventario, Órdenes)
**Archivos:**
- `servicio-producto/src/main/java/com/example/servicio_producto/config/CorsConfig.java`
- `servicio-inventario/src/main/java/com/example/servicio_inventario/config/CorsConfig.java`
- `servicio-ordenes/src/main/java/com/example/servicio_ordenes/config/CorsConfig.java`

Los microservicios usan **WebMvcConfigurer** (tradicional) porque están basados en Spring MVC.

**Configuración idéntica al Gateway:**
- ✅ Permite todos los orígenes (`*`)
- ✅ Métodos HTTP: GET, POST, PUT, DELETE, OPTIONS, PATCH
- ✅ Todos los headers permitidos
- ✅ Credenciales habilitadas
- ✅ Caché preflight: 1 hora

## 🚀 Cómo Usar

### Para Desarrollo (Actual)
La configuración actual está optimizada para desarrollo y permite **todos los orígenes** (`*`).

Tus aplicaciones frontend (React, Angular, Vue, etc.) pueden hacer peticiones desde:
- `http://localhost:3000`
- `http://localhost:4200`
- `http://127.0.0.1:3000`
- Cualquier otro puerto o dominio

### Para Producción (Recomendado)

**⚠️ IMPORTANTE:** En producción, debes especificar los orígenes exactos permitidos.

#### En el API Gateway, cambia:
```java
// Cambiar esto:
corsConfig.addAllowedOriginPattern("*");

// Por esto:
corsConfig.setAllowedOrigins(Arrays.asList(
    "https://tu-dominio.com",
    "https://www.tu-dominio.com",
    "https://app.tu-dominio.com"
));
```

#### En los microservicios, cambia:
```java
// Cambiar esto:
.allowedOriginPatterns("*")

// Por esto:
.allowedOrigins(
    "http://localhost:8080",  // Solo el API Gateway
    "https://tu-dominio.com"
)
```

## 🎯 Arquitectura Recomendada

```
Frontend (React/Angular/Vue)
    ↓
API Gateway :8080 ← [CORS aquí es suficiente]
    ↓
Microservicios (Producto, Inventario, Órdenes)
```

### ¿Dónde configurar CORS?

1. **Solo API Gateway:** Si tu frontend SOLO hace peticiones al Gateway (✅ Recomendado)
2. **Gateway + Microservicios:** Si algunos clientes acceden directamente a los microservicios

## 📝 Ejemplo de Petición desde Frontend

```javascript
// React/JavaScript
fetch('http://localhost:8080/api/productos', {
    method: 'GET',
    credentials: 'include', // Incluye cookies
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer tu-token'
    }
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

```javascript
// Axios
axios.get('http://localhost:8080/api/productos', {
    withCredentials: true, // Habilita envío de credenciales
    headers: {
        'Authorization': 'Bearer tu-token'
    }
})
.then(response => console.log(response.data))
.catch(error => console.error('Error:', error));
```

## 🔍 Verificar que CORS funciona

1. Inicia todos los servicios:
   - Eureka Server (8761)
   - API Gateway (8080)
   - Microservicios

2. Abre la consola del navegador (F12)

3. Ejecuta:
```javascript
fetch('http://localhost:8080/api/productos')
    .then(res => res.json())
    .then(data => console.log('✅ CORS funciona:', data))
    .catch(err => console.error('❌ Error CORS:', err));
```

4. Si ves los datos sin errores → ✅ CORS configurado correctamente
5. Si ves errores de CORS → ❌ Verifica la configuración

## 🛠️ Solución de Problemas

### Error: "Access-Control-Allow-Origin"
- Verifica que el servicio tenga el archivo `CorsConfig.java`
- Reinicia el servicio después de agregar la configuración

### Error: "Credentials mode is 'include'"
- Asegúrate de que `allowCredentials(true)` esté configurado
- No uses `allowedOrigins("*")` con credentials, usa `allowedOriginPatterns("*")`

### El preflight OPTIONS falla
- El método OPTIONS debe estar en `allowedMethods`
- Verifica que `maxAge` esté configurado

## 📋 Checklist de Seguridad para Producción

- [ ] Cambiar `allowedOriginPattern("*")` por dominios específicos
- [ ] Revisar métodos HTTP permitidos (quitar los que no uses)
- [ ] Limitar headers permitidos a los necesarios
- [ ] Considerar deshabilitar `allowCredentials` si no usas cookies
- [ ] Configurar HTTPS en producción
- [ ] Implementar rate limiting
- [ ] Agregar autenticación y autorización (JWT, OAuth2)

## 📚 Referencias
- [Spring CORS Documentation](https://docs.spring.io/spring-framework/reference/web/webflux-cors.html)
- [MDN CORS](https://developer.mozilla.org/es/docs/Web/HTTP/CORS)
- [Spring Cloud Gateway CORS](https://cloud.spring.io/spring-cloud-gateway/reference/html/#cors-configuration)
