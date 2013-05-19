package org.tw.test.routes;

import org.tw.test.models.Student;
import org.thoughtworks.orc.OrcController;
import org.thoughtworks.orc.annotations.Get;
import org.thoughtworks.orc.annotations.Post;
import org.thoughtworks.orc.annotations.Route;

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
        session("name",student.getBooks().get(0).getName());
    }
}
