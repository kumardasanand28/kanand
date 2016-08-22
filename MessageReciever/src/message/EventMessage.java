package message;

import java.io.Serializable;
import java.util.List;

public class EventMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private int messageId;

	private List<String> messageList;

	public EventMessage(int id, List<String> message) {

		this.messageId = id;

		this.messageList = message;

	}

	public int getMessageId() {

		return messageId;

	}

	public void setMessageId(int messageId) {

		this.messageId = messageId;

	}

	public List<String> getMessageText() {

		return messageList;

	}

	public void setMessageText(List<String> messageText) {

		this.messageList = messageText;

	}

	public String toString(){

		return "Message Id = "+getMessageId()+" ; Message Text = "+getMessageText();

	}

}