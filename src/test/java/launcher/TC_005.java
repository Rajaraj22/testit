package launcher;

public class TC_005 extends BaseTest {

	public static void main(String[] args) throws Exception {

		init();

		launch("chromebrowser");

		navigateUrl("amazonurl");

		selectOption("amazondropbox_id", "Books");

		typeText("amazonsearchtextbox_id", "Harry Potter");

		clickElement("amazonsearchbutton_xpath");

	}

}
