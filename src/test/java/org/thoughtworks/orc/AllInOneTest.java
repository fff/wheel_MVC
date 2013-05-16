package org.thoughtworks.orc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.orc.internal.Action;
import org.thoughtworks.orc.internal.ActionModule;
import org.thoughtworks.orc.internal.ViewModule;
import org.thoughtworks.orc.internal.util.HttpContext;
import org.thoughtworks.orc.internal.util.NamePair;
import org.thoughtworks.orc.test.routes.Some;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AllInOneTest {
    Injector injector;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private PrintWriter writer;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new Application("/Users/twer/w3/wheel/wheel_MVC/src/test/resources/views/", "mst"),
                new ActionModule("org.thoughtworks.orc.test.routes"), new ViewModule());
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        HttpContext.current.set(new HttpContext(request, response, session));
        writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void test_initial() throws Exception {
        assertNotNull(injector);
    }

    @Test
    public void test_route_some_is_OK() throws Exception {
        final Some some = injector.getInstance(Some.class);
        assertEquals(some.getClass(), Some.class);
    }

    @Test
    public void test_view_finder() throws Exception {
        final Action action = injector.getInstance(Action.class);
        action.execute(new NamePair("some", "test"));
        verify(writer, times(1)).print("{{hello}};hello,Hello world!");
    }
}
