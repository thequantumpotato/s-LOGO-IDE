package frontend.API;

public interface Speaker {
   // should also have a List<Listener> myListeners;

    void addListener();

    void removeListener();

    void updateAll();
}
