package org.thoughtworks.mvc;

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

public class InterceptServletTest {

    private InterceptServlet interceptServlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private Enumeration parameterNames;
    private HashMap<String,String[]> paramsMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {

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

        interceptServlet = new InterceptServlet();
        mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getParameterNames()).thenReturn(parameterNames);
        when(mockRequest.getParameterValues("text")).thenReturn(paramValuesText);
        when(mockRequest.getParameterValues("radio")).thenReturn(paramValuesRadio);
        when(mockRequest.getParameterValues("dropdowm")).thenReturn(paramValuesDropdown);

        mockResponse = mock(HttpServletResponse.class);
    }

    @Test
    public void testFillingDataIntoFormWhenMethodIsGet() throws Exception {

        interceptServlet.doGet(mockRequest, mockResponse);

        assertThat(interceptServlet.getParamsMap(), equalTo(paramsMap));
    }

    @Test
    public void testFillingDataIntoFormWhenMethodIsPost() throws Exception {

        interceptServlet.doPost(mockRequest, mockResponse);

        assertThat(interceptServlet.getParamsMap(), equalTo(paramsMap));
    }
}
