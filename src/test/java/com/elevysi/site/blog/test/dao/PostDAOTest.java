package com.elevysi.site.blog.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.BlogBootApplication;
import com.elevysi.site.blog.dao.PostDAO;
import com.elevysi.site.blog.dao.UploadDAO;
import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.Post_;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.service.CategoryService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PublicationStatusService;
import com.elevysi.site.blog.service.UploadService;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={BlogBootApplication.class})
@Transactional
public class PostDAOTest {

	private Post post;
	private final String testContent =  "Far far away, behind the word mountainsTesting_, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then";
	private final String testDescription = "software like Aldus PageMaker including versions of Lorem Ipsum";
	private final String testTitle = "UnreplicatableTestTitle123";
	private String usedUUID = Post.generateStaticUUID();
	private Set<Upload> uploads = new HashSet<Upload>();
	private List<Post> posts = new ArrayList<Post>();
	private ProfileDTO profile;
	private Publication publication;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UploadDAO uploadDAO;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private PublicationStatusService publicationStatusService;
	
	@Autowired
	private UploadService uploadService;
	
	
	
	@Before
	public void init(){
		
		profile = new ProfileDTO();
		profile.setId(1);
		profile.setName("Tester");
		
		post = new Post();
		post.setTitle(testTitle);
		post.setDescription(testDescription);
		post.setContent(testContent);
		post.setUuid(usedUUID);
		
		Date now = new Date();
		post.setCreated(now);
		post.setModified(now);
		
		
		publication = new Publication();
		publication.setFriendlyUrl("Test Slug");
		
		publication.setProfile(profile);
		publication.setProfileName(profile.getName());
		
		
		Set<Category> categories = new HashSet<Category>();
		Category cat1 = categoryService.findByID(1);
		Category cat2 = categoryService.findByID(2);
		categories.add(cat1);
		categories.add(cat2);
		
		Set<Dossier> dossiers = new HashSet<Dossier>();
		Dossier d1 = dossierService.findByID(19);
		Dossier d2 = dossierService.findByID(20);
		dossiers.add(d1);
		dossiers.add(d2);
		
		
		PublicationStatus postStatus = publicationStatusService.findPublishedPostStatus();
		publication.setPublicationStatus(postStatus);
		publication.setCategories(categories);
		publication.setDossiers(dossiers);
		
		
		
		
		Upload up1 = new Upload();
//		up1.setLinkId(savedPost.getId());
		up1.setLinkTable("posts");
//		up1.setId(1);
		up1.setUuid(usedUUID);
		up1.setDisplay(true);
		
		Upload up2 = new Upload();
//		up2.setLinkId(savedPost.getId());
		up2.setLinkTable("posts");
//		up1.setId(2);
		up2.setUuid(usedUUID);
		up2.setDisplay(true);
		
		Upload savedUp1 = uploadDAO.save(up1);
		Upload savedUp2 = uploadDAO.save(up2);
		
		
		uploads.add(savedUp1);
		uploads.add(savedUp2);
		
		
		post.setProfileID(1);
//		post.setUploads(uploads);
		post.setPublication(publication);
		
	}
	
	@Test
	public void testSaveFindPost() {
		
		Post savedPost = postDAO.save(post);
		assertEquals(post.getTitle(), savedPost.getTitle());
		assertEquals(post.getDescription(), savedPost.getDescription());
		assertEquals(post.getContent(), savedPost.getContent());
		assertEquals(post.getProfileID(), savedPost.getProfileID());
		assertEquals(post.getCreated(), savedPost.getCreated());
		assertEquals(post.getModified(), savedPost.getModified());
		assertEquals(post.getUuid(), savedPost.getUuid());
		
		assertEquals(post.getPublication().getDossiers(), savedPost.getPublication().getDossiers());
		assertEquals(post.getPublication().getCategories(), savedPost.getPublication().getCategories());
		assertEquals(publication.getPublicationStatus(), savedPost.getPublication().getPublicationStatus());
		assertEquals(publication.getProfile(), savedPost.getPublication().getProfile());
		
		/**
		 * Test Find the saved POST
		 */
		
		Post foundPost = postDAO.findByID(savedPost.getId());
		
		assertEquals(foundPost.getTitle(), savedPost.getTitle());
		assertEquals(foundPost.getDescription(), savedPost.getDescription());
		assertEquals(foundPost.getContent(), savedPost.getContent());
		assertEquals(foundPost.getProfileID(), savedPost.getProfileID());
		assertEquals(foundPost.getCreated(), savedPost.getCreated());
		assertEquals(foundPost.getModified(), savedPost.getModified());
		assertEquals(foundPost.getUuid(), savedPost.getUuid());
		
		assertEquals(foundPost.getPublication().getDossiers(), savedPost.getPublication().getDossiers());
		assertEquals(foundPost.getPublication().getCategories(), savedPost.getPublication().getCategories());
		
		
//		assertEquals(foundPost.getUploads(), uploads);
		
		assertEquals(foundPost.getPublication().getFriendlyUrl(), publication.getFriendlyUrl());
		assertEquals(foundPost.getPublication().getPublicationStatus(), savedPost.getPublication().getPublicationStatus());
		
		assertEquals(foundPost.getPublication().getProfile(), profile);
		assertEquals(foundPost.getPublication().getProfileName(), profile.getName());
//		assertEquals(foundPost.getPublication().getProfileID(), 1L);
		
		/**
		 * Test Pages
		 */
		
		Page page = postDAO.buildOffsetPage(1, 1, Post_.created, SortDirection.DESC);
		posts.add(savedPost);
		
		List<Post> foundPosts = postDAO.getPosts(page);
		assertEquals(posts, foundPosts);
		
		List<Post> latestForProfile = postDAO.getAllLatestForProfile(profile, page);
		assertEquals(posts, latestForProfile);
		
		List<Post> latestForProfileExcept = postDAO.getAllLatestForProfileExcept(profile, savedPost.getId(), page);
		assertEquals(new ArrayList<Post>(), latestForProfileExcept);
		
		Post foundInexistingPost = postDAO.findByID(-1);
		assertEquals(null, foundInexistingPost);
		
		
		/**
		 * Test search by Term
		 */
		assertEquals(posts, postDAO.searchByTerm(testTitle)); //Title
		assertEquals(posts, postDAO.searchByTerm(testDescription)); //Description
		assertEquals(posts, postDAO.searchByTerm("mountainsTesting_, far from the countries Vokalia and Consonantia, there l")); //Content
		assertEquals(new ArrayList<Post>(), postDAO.searchByTerm("abdcNonExistant"));
		
		/**
		 * Test delete
		 */
		postDAO.delete(savedPost.getId());
		assertEquals(null, postDAO.findByID(savedPost.getId()));
	}
	
}
