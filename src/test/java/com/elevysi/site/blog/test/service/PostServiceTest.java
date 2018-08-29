package com.elevysi.site.blog.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.elevysi.site.blog.dao.CommentDAO;
import com.elevysi.site.blog.dao.PostDAO;
import com.elevysi.site.blog.dao.PublicationDAO;
import com.elevysi.site.blog.dao.UploadDAO;
import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.service.PostService;
import com.elevysi.site.blog.service.PublicationService;
import com.elevysi.site.commons.dto.ProfileDTO;

public class PostServiceTest {
	
	private PostDAO postDAOMock = mock(PostDAO.class);
	private PublicationDAO publicationDAOMock = mock(PublicationDAO.class);
	private UploadDAO uploadDAOMock = mock(UploadDAO.class);
	private CommentDAO commentDAO = mock(CommentDAO.class);
	private Post post;
	private Post dAOSavedPost;
	private final String testContent =  "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then";
	
	private PostService postService = new PostService(postDAOMock);
	private ModelMapper modelMapper;
	
	private PublicationService publicationService = new PublicationService(publicationDAOMock, modelMapper);
	
	private Publication publication;
	
	private ProfileDTO profileDTO;
	
	@Before
	public void init(){
		
		postService.setPublicationService(publicationService);
		
		profileDTO = new ProfileDTO();
		profileDTO.setId(1);
		
		post = new Post();
		post.setId(1);
		post.setTitle("Test Titlte");
		post.setDescription("This is a test description");
		post.setContent(testContent);
		
		Date now = new Date();
		post.setCreated(now);
		post.setModified(now);
		
		Dossier dossier = new Dossier();
		dossier.setId(2);
		
		Set<Category> categories = new HashSet<Category>();
		Category cat1 = new Category();
		cat1.setId(1);
		
		Category cat2 = new Category();
		cat2.setId(2);
		
		categories.add(cat1);
		categories.add(cat2);
		
		PublicationStatus postStatus = new PublicationStatus();
		postStatus.setId(1);
		
//		post.setDossier(dossier);
//		post.setCategories(categories);
		post.setProfileID(1);
//		post.setPostStatus(postStatus);
		post.setProfileID(1);
		post.setProfile(profileDTO);
		
		
		
		dAOSavedPost = new Post();
		dAOSavedPost.setId(1);
		dAOSavedPost.setTitle("Test Titlte");
		dAOSavedPost.setDescription("This is a test description");
		dAOSavedPost.setContent(testContent);
		dAOSavedPost.setCreated(now);
		dAOSavedPost.setModified(now);
		
//		dAOSavedPost.setDossier(dossier);
//		dAOSavedPost.setCategories(categories);
		dAOSavedPost.setProfileID(1);
//		dAOSavedPost.setPostStatus(postStatus);
		dAOSavedPost.setProfileID(1);
		dAOSavedPost.setProfile(profileDTO);
		
		
		publication = new Publication();
		publication.setId(new Long(1));
		publication.setPost(post);
		publication.setProfile(profileDTO);
		publication.setProfileID(1);
		
		dAOSavedPost.setPublication(publication);
			
	}
	
	@Test
	public void testSavePost() {
		
		when(postDAOMock.save(this.post)).thenReturn(dAOSavedPost);
		when(publicationDAOMock.save(this.publication)).thenReturn(publication);
		
		Post savedPost = postService.savePost(post);
		
		assertEquals(dAOSavedPost.getId(), savedPost.getId());
		assertEquals(dAOSavedPost.getTitle(), savedPost.getTitle());
		assertEquals(dAOSavedPost.getDescription(), savedPost.getDescription());
		assertEquals(dAOSavedPost.getContent(), savedPost.getContent());
		assertEquals(dAOSavedPost.getProfileID(), savedPost.getProfileID());
		assertEquals(dAOSavedPost.getCreated(), savedPost.getCreated());
		assertEquals(dAOSavedPost.getModified(), savedPost.getModified());
		
//		assertEquals(dAOSavedPost.getDossier(), savedPost.getDossier());
//		assertEquals(dAOSavedPost.getCategories(), savedPost.getCategories());
//		assertEquals(dAOSavedPost.getPostStatus(), savedPost.getPostStatus());
		
		assertEquals(publication, savedPost.getPublication());
		
	}
	
	@Test
	public void testLoadPlayForView(){
		when(postDAOMock.findByID(this.post.getId())).thenReturn(dAOSavedPost);
		
		Post foundPost = postService.findByID(post.getId());
		
		assertEquals(dAOSavedPost.getTitle(), foundPost.getTitle());
		assertEquals(dAOSavedPost.getDescription(), foundPost.getDescription());
		assertEquals(dAOSavedPost.getContent(), foundPost.getContent());
		assertEquals(dAOSavedPost.getProfileID(), foundPost.getProfileID());
		assertEquals(dAOSavedPost.getCreated(), foundPost.getCreated());
		assertEquals(dAOSavedPost.getModified(), foundPost.getModified());
		
//		assertEquals(dAOSavedPost.getDossier(), foundPost.getDossier());
//		assertEquals(dAOSavedPost.getCategories(), foundPost.getCategories());
//		assertEquals(dAOSavedPost.getPostStatus(), foundPost.getPostStatus());
	}
	

}
