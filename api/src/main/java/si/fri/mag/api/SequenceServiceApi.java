package si.fri.mag.api;


import si.fri.mag.api.controllers.MainController;
import si.fri.mag.api.controllers.RootController;
import si.fri.mag.api.controllers.v1.SequenceController;
import si.fri.mag.api.mappers.ForbiddenExceptionMapper;
import si.fri.mag.api.mappers.InternalServerErrorMapper;
import si.fri.mag.api.mappers.NotFoundExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class SequenceServiceApi extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(ForbiddenExceptionMapper.class);
        resources.add(InternalServerErrorMapper.class);
        resources.add(NotFoundExceptionMapper.class);
        resources.add(MainController.class);
        resources.add(RootController.class);
        resources.add(SequenceController.class);
        return resources;
    }
}
