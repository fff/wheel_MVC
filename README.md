## Wheel MVC
### 功能要求

1. The implementation must support model driven page render with at least one choice of template engines(JSP, FreeMarker，StringTemplate，Velocity，Mustache)
2. The implementation must support parameterForm submit, including nested structure.
3. The implementation must support service injection for controller, and must use your di container you finished last round(unless the loser who choose to use Guice)
4. The implementation must support run in a embedded web container(Grizzly, Jetty or Tomcat)
5. The support of web.xml is not mandatory

### 实现要求

1. Functioning properly, and robust.
2. No other library apart from guava, xunit, testing tools, servlet api, container and the template engine you chose.

---------
### Blue Print

	@route("/some")
	class SomeController extends Controller{
		
		@Post
		//deal with the POST request to '/some/test?a=_&bb=__'
		public void test(A a, BB bb){
			SomeForm parameterForm = parameterForm(SomeForm.class)...
			String name = session("name");
			session("key", "value");
			flash("result", "success");
			model("one","1");
			//will render with /some/test.html.mst and model
		}
	}	

