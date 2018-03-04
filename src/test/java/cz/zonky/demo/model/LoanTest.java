package cz.zonky.demo.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoanTest {

	@Test
	public void testPhotos() {
		assertThat(new Loan().getPhotos()).isNotNull();
		
		Photo photo = new Photo();
		photo.setName("photo");
		
		Loan loan = new Loan();
		loan.setPhotos(Arrays.asList(photo));
		assertThat(loan.getPhotos()).contains(photo);
		
		loan.setPhotos(null);
		assertThat(loan.getPhotos()).isNotNull();
		assertThat(loan.getPhotos()).isEmpty();
	}
	
	@Test
	public void testEquals() {
		assertThat(new Loan()).isEqualTo(new Loan());
		assertThat(new Loan.Builder(1L).build()).isEqualTo(new Loan.Builder(1L).build());
		assertThat(new Loan.Builder(1L).name("some").build()).isEqualTo(new Loan.Builder(1L).build());
		assertThat(new Loan.Builder(1L).build()).isEqualTo(new Loan.Builder(1L).story("some").build());
	}
	
	@Test
	public void testHashCode() {
		assertThat(new Loan().hashCode()).isEqualTo(new Loan().hashCode());
		assertThat(new Loan.Builder(1L).build().hashCode()).isEqualTo(new Loan.Builder(1L).build().hashCode());
		assertThat(new Loan.Builder(1L).name("some").build().hashCode()).isEqualTo(new Loan.Builder(1L).build().hashCode());
		assertThat(new Loan.Builder(1L).build().hashCode()).isEqualTo(new Loan.Builder(1L).story("some").build().hashCode());
	}
	
	
}
