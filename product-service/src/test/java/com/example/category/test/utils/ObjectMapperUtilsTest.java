package com.example.category.test.utils;

import com.example.product.utils.ObjectMapperUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectMapperUtilsTest {

	@Test
	public void testMappingList() {
		final List<TestDTO> maps = new ArrayList<>();
		final int listSize=6;
		for (int i = 0; i < listSize; i++) {
			final TestDTO dto = new TestDTO();
			dto.setFirstName("TestUserFirstName" + i);
			dto.setLastName("TestUserLastName" + i);
			dto.setDob("13-05-" + i);
			dto.setEmail("test_" + i + "@gmail.com");
			dto.setPhone("00000" + i);
			maps.add(dto);
		}
		List<TestTransformDTO> mappedObject = ObjectMapperUtils.mapAll(maps, TestTransformDTO.class);

		Assert.assertEquals(mappedObject.size(), listSize);
		AtomicInteger i = new AtomicInteger();
		mappedObject.forEach(resp -> {
			Assert.assertEquals("TestUserFirstName" + i, resp.getFirstName());
			Assert.assertEquals("TestUserLastName" + i, resp.getLastName());
			i.getAndIncrement();
		});
	}

	@Test
	public void testMapping() {

		final TestDTO dto = new TestDTO();
		dto.setFirstName("TestUserFirstName");
		dto.setLastName("TestUserLastName");
		dto.setDob("13-05-");
		dto.setEmail("test_12@gmail.com");
		dto.setPhone("00000");
		TestTransformDTO mappedObject = ObjectMapperUtils.map(dto, TestTransformDTO.class);
		Assert.assertEquals("TestUserFirstName" , mappedObject.getFirstName());
		Assert.assertEquals("TestUserLastName" , mappedObject.getLastName());
	}

	public static class TestTransformDTO {
		private String firstName;
		private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}

	public static class TestDTO {
		private String firstName;
		private String lastName;
		private String dob;
		private String email;
		private String phone;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
	}
}
