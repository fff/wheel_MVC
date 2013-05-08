package org.thoughtworks.orc.internal;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RootServletTest {
    private RootServlet rootServlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private Enumeration parameterNames;
    private HashMap<String, String[]> paramsMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {

        rootServlet = new RootServlet();

        Vector vector = new Vector();
        vector.add("text");
        vector.add("radio");
        vector.add("dropdowm");
        parameterNames = vector.elements();

        String[] paramValuesText = new String[1];
        paramValuesText[0] = "Hello, MVC!";
        String[] paramValuesRadio = new String[1];
        paramValuesRadio[0] = "true";
        String[] paramValuesDropdown = new String[1];
        paramValuesDropdown[0] = "1";

        paramsMap.put("text", paramValuesText);
        paramsMap.put("radio", paramValuesRadio);
        paramsMap.put("dropdowm", paramValuesDropdown);

        mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getParameterNames()).thenReturn(parameterNames);
        when(mockRequest.getParameterValues("text")).thenReturn(paramValuesText);
        when(mockRequest.getParameterValues("radio")).thenReturn(paramValuesRadio);
        when(mockRequest.getParameterValues("dropdowm")).thenReturn(paramValuesDropdown);

        mockResponse = mock(HttpServletResponse.class);
    }

    @Test
    public void testFillingDataIntoFormWhenMethodIsGet() throws Exception {

        rootServlet.doGet(mockRequest, mockResponse);

        assertThat(rootServlet.getParamsMap(), equalTo(paramsMap));
    }

    @Test
    public void testFillingDataIntoFormWhenMethodIsPost() throws Exception {

        rootServlet.doPost(mockRequest, mockResponse);

        assertThat(rootServlet.getParamsMap(), equalTo(paramsMap));
    }
}
