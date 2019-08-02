package stepdef;

import java.util.List;

public class Messages {

	private static ThreadLocal<List<String>> messages = new ThreadLocal<>();
	
	private Messages() {}
	
	public static List<String> getMessages() {
		return messages.get();
	}
	
	public static void setMessages(List<String> msgs) {
		messages.set(msgs);
	}
}
