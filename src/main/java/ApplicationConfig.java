
import domain.controllers.*;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> controllers = new java.util.HashSet<>();
        addRestControllers(controllers);
        return controllers;
    }

    private void addRestControllers(Set<Class<?>> controllers) {
        controllers.add(GameController.class);
        controllers.add(GamestoreController.class);
        controllers.add(CustomerController.class);
        controllers.add(EmployeeController.class);
        controllers.add(ProductController.class);
        controllers.add(ReviewController.class);
    }
}