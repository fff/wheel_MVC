package org.thoughtworks.orc.test.routes;

import org.thoughtworks.orc.OrcController;
import org.thoughtworks.orc.annotations.Get;
import org.thoughtworks.orc.annotations.Post;
import org.thoughtworks.orc.annotations.Route;
import org.thoughtworks.orc.test.model.Student;

@Route("/some")
public class Some extends OrcController {

    @Get
    public void test() {
        model("hello", "Hello world!");
    }

    @Post
    public void collect() {
        final Student student = form(Student.class);
        model("student", student);
    }
}
