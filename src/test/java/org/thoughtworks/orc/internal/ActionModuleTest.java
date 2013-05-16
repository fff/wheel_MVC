package org.thoughtworks.orc.internal;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.Assert;
import org.junit.Test;

public class ActionModuleTest {


    @Test
    public void test_get_pkg_dir_path() throws Exception {
        final String path = ActionModule.pkgDirPath("org.thoughtworks.orc.test.routes");
        Assert.assertEquals("org/thoughtworks/orc/test/routes", path);
    }

    @Test
    public void test_initial() throws Exception {
        final Injector injector = Guice.createInjector(new ActionModule("org.thoughtworks.orc.test.routes"));
        Assert.assertNotNull(injector);
    }
}
