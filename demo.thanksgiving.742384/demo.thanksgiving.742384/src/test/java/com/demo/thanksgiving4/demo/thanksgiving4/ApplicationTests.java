package com.demo.thanksgiving4.demo.thanksgiving4;

import com.demo.thanksgiving4.demo.thanksgiving4.controller.ItemController;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.ItemRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ApplicationTests {

	@Mock
	private ItemRepository itemRepository;


	@Before
	public void setUp() {

	}


        @Ignore
		@Test
		public void saveTest() {

			ItemController itemController = new ItemController(itemRepository);
			//ResponseEntity responseEntity = mock(ResponseEntity.class);
			Item expected = new Item();
			expected.setItemId(11L);
			expected.setItemName("sword1");

			Item item2 = new Item();
			item2.setItemId(12L);
			item2.setItemName("sword2");
			//List<Item> lessons = Arrays.asList(item1, item2);
			when(itemController.create(expected)).thenReturn(ResponseEntity.ok().build());
			ResponseEntity result = itemController.create(expected);
			assertThat(itemController.create(expected), is(result));

			//verify(itemController.create(any(Item.class)), times(1));
			//when(itemRepository.findById(11L)).thenReturn(Optional.of(new Item()));
			//assertThat(itemRepository.save(item1), is(item1));
			//assertThat(itemRepository.save(item1).getItemName(), is("sword1"));

		}

	@Test
	public void getAllItemsTest() {

		ItemController itemController = new ItemController(itemRepository);

		Item item1 = new Item();
		item1.setItemId(11L);
		item1.setItemName("sword1");

		itemController.create(item1);
		Item item2 = new Item();
		item2.setItemId(12L);
		item2.setItemName("sword2");
		itemController.create(item2);

		Iterable<Item> expectedList = Arrays.asList(item1, item2);
		when(itemController.getAllItems()).thenReturn(expectedList);
		Iterable<Item>  resultList= itemController.getAllItems();
		assertThat(itemController.getAllItems(), is(resultList));

	}

}
