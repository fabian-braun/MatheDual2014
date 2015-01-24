package system;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;

public class DropTo {

	private static Logger log = LoggerFactory.getLogger(DropTo.class);

	@Test
	public void test() {
		Area area = new Area(10, 10);
		log.debug(((Boolean) area.dropTo(0, (byte) 5)).toString());
		log.debug(((Boolean) area.dropTo(5, (byte) 3)).toString());
		log.debug(((Boolean) area.dropTo(0, (byte) 3)).toString());
		log.debug(((Boolean) area.dropTo(0, (byte) 3)).toString());
		log.debug(((Boolean) area.dropTo(0, (byte) 2)).toString());
		log.debug(((Boolean) area.dropTo(0, (byte) 2)).toString());
		log.debug(((Boolean) area.dropTo(2, (byte) 2)).toString());
		log.debug(((Boolean) area.dropTo(4, (byte) 5)).toString());
		log.debug(area.toString());
	}
}
