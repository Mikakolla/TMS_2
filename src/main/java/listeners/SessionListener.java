package listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("destroyed Session");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("start Session");
    }
}
