# FWeb
Java WebServer using Reflect to allow for easy application

Supports
-----
| Method  | Support |
| ------------- | ------------- |
| GET  | YES  |
| POST  | YES  |
| PUT  | YES  |
| HEAD  | YES  |
| HTTP  | YES  |
| HTTPS  | NOT YET  |

Usage
-----
Here are some examples of how to use the FWeb library.

**Start FWeb**
```Java
WebServer web = new WebServer(8080);
web.registerController(Index.class); //INITIALIZE A CLASS OF METHODS
web.start();
```

**Index Methods**
```Index
@Controller(host = "127.0.0.1:8080") //NOT REQUIRED
public class Index {

    @Mapping(location = "/")
    public void onResponse(GetEvent event)throws IOException {
        System.out.println("INDEX FILE");
        event.getOutputStream().write("HELLO WORLD".getBytes());
    }
}
```
