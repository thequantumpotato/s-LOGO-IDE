module FrontEnd {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    exports frontend.ExternalAPI;
    exports frontend.Util;
    exports frontend.GUI;
    exports main;
}
