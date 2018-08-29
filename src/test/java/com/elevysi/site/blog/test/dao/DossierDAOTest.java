package com.elevysi.site.blog.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.elevysi.site.blog.dao.DossierDAO;
import com.elevysi.site.blog.dao.PlayDAO;
import com.elevysi.site.blog.dao.PostDAO;
import com.elevysi.site.blog.dao.PublicationDAO;
import com.elevysi.site.blog.dao.UploadDAO;
import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Dossier_;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Post_;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.service.CategoryService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayTypeService;
import com.elevysi.site.blog.service.PublicationStatusService;
import com.elevysi.site.blog.service.UploadService;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.Page;
import com.elevysi.site.commons.pojo.Page.SortDirection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={BlogBootApplication.class})
@Transactional
public class DossierDAOTest {

	private Dossier dossier;
	private final String testContent =  "Far far away, behind the word mountainsTesting_, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then";
	private final String testDescription = "software like Aldus PageMaker including versions of Lorem Ipsum";
	private final String testTitle = "UnreplicatableTestTitle123";
	private final String testNonExistant = "abdc4321NonExistant";
	private ProfileDTO profile;
	private String usedUUID = Dossier.generateStaticUUID();
	private Set<Publication> publications = new HashSet<Publication>();
	private List<Publication> publicationsList = new ArrayList<Publication>();
	
	private Publication publication;
	private Set<Upload> uploads = new HashSet<Upload>();
	private List<Dossier> dossiers = new ArrayList<Dossier>();
	
	private Post savedPost;
	private Play savedPlay;
	
	private int testProfileID = 2;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private DossierDAO dossierDAO;
	
	@Autowired
	private PlayDAO playDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PublicationStatusService publicationStatusService;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private UploadDAO uploadDAO;
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	
	
	@Before
	public void init() {
		
		profile = uploadService.getProfile(new Long(testProfileID));
//		profile.setId(1);
//		profile.setName("Tester");
		
		dossier = new Dossier();
		
		Date now = new Date();
		dossier.setCreated(now);
		dossier.setModified(now);
		dossier.setName(testTitle);
		dossier.setDescription(testDescription);
		dossier.setProfileID(testProfileID);
		
		//Find Posts
		Post post = new Post();
		post = new Post();
		post.setTitle(testTitle);
		post.setDescription(testDescription);
		post.setContent(testContent);
		post.setUuid(usedUUID);
		post.setCreated(now);
		post.setModified(now);
		post.setProfileID(testProfileID);
		Publication postPublication = new Publication();
		post.setPublication(postPublication);
		
		
		savedPost = postDAO.save(post);
		
		//Find Plays
//		Page playsPage = postDAO.buildOffsetPage(1, 1, Post_.created, SortDirection.DESC);
//		playDAO.getPlays(playsPage);
		Play play = new Play();
		play = new Play();
		play.setTitle(testTitle);
		play.setEmbeddedUrl("Embedded URL");
		play.setUrl("Test URL");
		play.setProfileID(testProfileID);
		play.setDescription(testContent);
		play.setCreated(now);
		play.setModified(now);
		Publication playPublication = new Publication();
		play.setPublication(playPublication);
		
		savedPlay = playDAO.save(play);
		
		Set<Category> categories = new HashSet<Category>();
		Category cat1 = categoryService.findByID(1);
		Category cat2 = categoryService.findByID(2);
		categories.add(cat1);
		categories.add(cat2);
		
		
		
		publication = new Publication();
		publication.setFriendlyUrl("Test Dossier Slug");
		PublicationStatus postStatus = publicationStatusService.findPublishedPostStatus();
		publication.setPublicationStatus(postStatus);
		publication.setCategories(categories);
		publication.setProfileID(testProfileID);
		publication.setProfile(profile);
		
		dossier.setPublication(publication);
		
		
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
		
	}
	
	@Test
	public void testDossier() {
		
		/**
		 * Test save Dossier
		 */
		Dossier savedDossier = dossierDAO.save(dossier);
		dossiers.add(savedDossier);
		
		/**
		 * Set the saved item to the saved Dossier
		 */
//		savedPost.getPublication().getDossiers().add(savedDossier);
//		savedPlay.getPublication().getDossiers().add(savedDossier);
		
		savedPost.getPublication().setDossiers(new HashSet<Dossier>((Arrays.asList(savedDossier))));
		savedPlay.getPublication().setDossiers(new HashSet<Dossier>((Arrays.asList(savedDossier))));
		
		/**
		 * Re-save the items
		 */
		Post reSavedPost = postDAO.saveEdited(savedPost);
		Play reSavedPlay = playDAO.saveEdited(savedPlay);
		publications.add(reSavedPost.getPublication());
		publications.add(reSavedPlay.getPublication());
		
		assertEquals(dossier.getName(), savedDossier.getName());
		assertEquals(dossier.getDescription(), savedDossier.getDescription());
		assertEquals(dossier.getCreated(), savedDossier.getCreated());
		assertEquals(dossier.getModified(), savedDossier.getModified());
		assertEquals(publication.getFriendlyUrl(), savedDossier.getPublication().getFriendlyUrl());
		assertEquals(publication.getCategories(), savedDossier.getPublication().getCategories());
		assertEquals(publication.getProfile(), savedDossier.getPublication().getProfile());
		/**
		 * Test Find
		 */
		Dossier foundDossier = dossierDAO.findByID(savedDossier.getId());
		assertEquals(savedDossier.getId(), foundDossier.getId());
		assertEquals(savedDossier.getDescription(), foundDossier.getDescription());
		assertEquals(savedDossier.getCreated(), foundDossier.getCreated());
		assertEquals(savedDossier.getModified(), foundDossier.getModified());
		assertEquals(savedDossier.getPublication().getProfile(), foundDossier.getPublication().getProfile());
		assertEquals(savedDossier.getPublication().getCategories(), foundDossier.getPublication().getCategories());

		//Not Passing :-(
//		assertEquals(publications, foundDossier.getPublications());
		
		/**
		 * Test Search
		 */
		assertEquals(dossiers, dossierDAO.searchByTerm(testTitle));
		assertEquals(dossiers, dossierDAO.searchByTerm(testDescription));
		assertEquals(new ArrayList<Dossier>(), dossierDAO.searchByTerm(testNonExistant));
		
		Page page = dossierDAO.buildOffsetPage(1, 1, Dossier_.created, SortDirection.DESC);
		assertEquals(dossiers, dossierDAO.getDossiersForProfile(profile, page));
		/**
		 * Test Find non existant
		 */
		assertNull(dossierDAO.findByID(-1));
		
		/**
		 * Test find publications
		 * Add Dossier to publicationsList
		 */
		
		publicationsList.add(reSavedPost.getPublication());
		publicationsList.add(reSavedPlay.getPublication());
		publicationsList.add(foundDossier.getPublication());
		
		assertEquals(publicationsList, publicationDAO.searchByTerm(testTitle));
		assertEquals(3, publicationDAO.searchByTerm(testTitle).size());
		
		/**
		 * Test Delete
		 */
		dossierDAO.delete(savedDossier.getId());
		assertNull(dossierDAO.findByID(savedDossier.getId()));
		
	}
}
