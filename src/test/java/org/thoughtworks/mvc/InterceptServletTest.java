package org.thoughtworks.mvc;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterceptServletTest {

    private HttpServlet httpServlet;

    @Before
    public void setUp() throws Exception {
        httpServlet = mock(HttpServlet.class);
        when(httpServlet.getServletName()).thenReturn("");
    }

    @Test
    public void testGetHttpHeadFromServlet() throws Exception {
        InterceptServlet interceptServlet = new InterceptServlet();
    }
}
