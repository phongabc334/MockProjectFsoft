package group2.services.client;

import java.util.*;

import group2.models.Book;
import group2.models.BookCase;
import group2.utils.Constant;
import group2.utils.File;

public class BookCaseServices {
	static List<BookCase> listBookCase = new ArrayList<>();
	static List<Book> listBook = new ArrayList<>();

	// View book case
	public static void viewBookCase(int userId) {
		if ((group2.utils.File.read(group2.utils.Constant.BOOKCASE_PATH)) == null) {
			System.out.println("You don't have BookCase!");
		} else {
			listBookCase = group2.utils.File.read(group2.utils.Constant.BOOKCASE_PATH);
			for (BookCase bk : listBookCase) {
				if (bk.getBook_case_id() == userId) {
					if (bk.getListBook().size() == 0) {
						System.out.println("Your BookCase is empty!");
					} else {
						System.out.println("Your BookCase has " + bk.getListBook().size() + " books:");
						System.out.printf("%-8s %-15s %-20s %-15s %-18s %s\n", "ID", "Name", "Title", "Author",
								"Publisher", "Category");
						Iterator<Book> iterator = bk.getListBook().iterator();
						while (iterator.hasNext()) {
							Book book = new Book();
							book = iterator.next();
							System.out.printf("%-8s %-15s %-20s %-15s %-18s %s\n", book.getBook_id(),
									book.getBook_name(), book.getBook_title(), book.getAuthor(), book.getPublisher(),
									book.getCategory());
						}

					}
					break;
				}
			}
		}
	}
	// Edit book case (add by id, remove by id)

	public static void addBook(int userId, int bookId) {
		listBookCase = group2.utils.File.read(group2.utils.Constant.BOOKCASE_PATH);
		listBook = group2.utils.File.read(group2.utils.Constant.BOOK_PATH);
		Boolean check = false;
		for (BookCase bk : listBookCase) {
			if (bk.getBook_case_id() == userId) {
				int size = bk.getListBook().size();
				for (Book book : listBook) {
					if (book.getBook_id() == bookId) {
						bk.getListBook().add(book);
						if(size == bk.getListBook().size()) {
							System.out.println("Book is existed in Book case");
							check = true;
							break;
						}
						System.out.println("Add successfully !");
						check = true;
						break;
					}
					else {
//						System.out.println("Add Fail !");
					}
				}
				// System.out.println("aa");
				break;
			}
		}
		if(!check) {
			System.out.println("Book is not exists!");
		}
		if (!File.write(Constant.BOOKCASE_PATH, listBookCase)) {
			System.out.println("Error File.write");
		}
	}

	public static void removeBook(int userId, int bookId) {
		listBookCase = group2.utils.File.read(group2.utils.Constant.BOOKCASE_PATH);
		Boolean check = false;
		for (BookCase bk : listBookCase) {
			if (bk.getBook_case_id() == userId) {
				for (Book book : bk.getListBook()) {
					if (book.getBook_id() == bookId) {
						bk.getListBook().remove(book);
						System.out.println("Remove successfully !");
						check = true;
						break;
					}
				}
				break;
			}
		}
		if(!check) {
			System.out.println("Book is not exists!");
		}
		if (!File.write(Constant.BOOKCASE_PATH, listBookCase)) {
			System.out.println("Error File.write");
		}
	}

	public static void clearBook(int userId) {
		listBookCase = group2.utils.File.read(group2.utils.Constant.BOOKCASE_PATH);
		for (BookCase bk : listBookCase) {
			if(bk.getBook_case_id() == userId) {
				bk.getListBook().clear();
				System.out.println("Remove All successfully !");
				break;
			}
		}
		if (!File.write(Constant.BOOKCASE_PATH, listBookCase)) {
			System.out.println("Error File.write");
		}

	}

}