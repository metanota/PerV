package src.ru.md24inc.alembic.pervoc.control;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleTest {
	public static void main(String args[]) {
		Card myCard = new Card();
		myCard.setWord("God");
		myCard.setTranscript("gad");
		myCard.setTranslation("���");
		System.out.println(myCard.getWord() + " " + myCard.getTranscript()
				+ " " + myCard.getTranslation());
		Card myCard2 = new Card("hub", "Hab", "������������, ��� ����� ������");
		System.out.println(myCard2.getWord() + " - [" + myCard2.getTranscript()
				+ "] - " + myCard2.getTranslation());
	}
}
