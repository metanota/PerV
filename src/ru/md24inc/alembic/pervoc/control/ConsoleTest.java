package src.ru.md24inc.alembic.pervoc.control;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleTest {
	public static void main(String args[]) {
		// Initializing New Vocabular
		Vocabulary myVocabulary = new Vocabulary();

		// Filling each card with some words and info
		Card[] myCards = new Card[3];
		// � ����� �� �������� ���-8 �� ������� �� �� ��������. � ��� ������
		myCards[0] = new Card("Test", "S\u00ED Se\u00F1or", "����������"); 
		myCards[1] = new Card("God", "gad", "���");
		myCards[2] = new Card("hub", "Hab", "������������, ��� ����� ������");

		// Inserting cards into vocabulary
		myVocabulary.add(myCards[0]);
		myVocabulary.add(myCards[1]);
		myVocabulary.add(myCards[2]);
		
		// Printing Vocabular
		myVocabulary.printAll();
	}
}
