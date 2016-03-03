package wireformats;

import java.io.IOException;

/**
 * This is an interface that defines two methods getBytes() and getType().
 * All wireformats must implement this interface.
 *
 */

public interface Event {
	
	byte[] getBytes() throws IOException;
	
	int getType();

}
