package org.example;

@Deprecated
public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("Disinfection is getting start. Everybody, go out!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Disinfection is over. Everybody can back!");
    }

    private void desinfect(Room room) {
        System.out.println("Corona, go out! Virus is deleted.");
    }
}
