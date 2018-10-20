module BackEnd {
    requires javafx.graphics;
    requires javafx.controls;
//    requires FrontEnd;

    exports backend;
    exports frontend;
    exports backend.Nodes;
}