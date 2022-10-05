package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the list of messages of a given room
 */
public class Messages {
    public List<Message> messages;

    public Messages() {
        messages = new ArrayList<>();
    }

    /**
     * Write the list of messages in basic HTML format, with
     * just a <ul></ul> containing every message
     * @param styleSender custom style to be applied if the sender was the logged user
     * @param styleReceiver custom style to be applied if the sender was not the logged user
     * @param sender username of the currently logged-in user
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styleSender, String styleReceiver, String sender) {
        StringBuilder messageList = new StringBuilder();
        // Messages must be ordered from newest to oldest
        for (int i = messages.size() - 1; i >= 0; i--) {
            messageList.append(messages.get(i).toHTML(styleSender, styleReceiver, sender));
        }
        return "<ul class='flex flex-col flex-auto overflow-auto'>" +
                messageList +
                "</ul>";
    }

    /**
     * Add a new message to the list of messages
     * @param message message to be added to the list
     */
    synchronized public void add(Message message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Messages{" + "messages=[" + messages.stream().map(Message::toString).map(String::valueOf).collect(Collectors.joining(",")) + "]}";
    }
}
