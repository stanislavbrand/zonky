package cz.zonky.demo.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeDeserializerTest {

	@Mock
	private JsonParser jp;
	
	
	private LocalDateTimeDeserializer localDateDeserializer;

	
	@Before
	public void setup() {
		localDateDeserializer = new LocalDateTimeDeserializer();
	}
	
	@Test
	public void testValid() throws IOException{
		when(jp.readValueAs(any(Class.class))).thenReturn("2015-06-24T09:52:26.991+02:00");
		
		LocalDateTime result = localDateDeserializer.deserialize(jp, null);
		assertThat(result).isEqualTo(LocalDateTime.of(2015, Month.JUNE, 24, 9, 52, 26,991000000));
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testInValidFormat() throws IOException{
		when(jp.readValueAs(any(Class.class))).thenReturn("2015-66-24T09:52:26.991+02:00");
		
		localDateDeserializer.deserialize(jp, null);
	}
	
	@Test(expected = IOException.class)
	public void testIOException() throws IOException{
		when(jp.readValueAs(any(Class.class))).thenThrow(IOException.class);
		
		localDateDeserializer.deserialize(jp, null);
	}
}
