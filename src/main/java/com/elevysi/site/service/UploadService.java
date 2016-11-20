package com.elevysi.site.service;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.repository.UploadDAO;
import com.elevysi.site.repository.UploadRepository;

@Service
public class UploadService extends AbstractService{
	
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UploadDAO uploadDAO;

	public Upload saveUpload(Upload upload) {
		
		
		Date now = new Date();
		
		/**
		 * Check if there already
		 */
		Integer objectID = upload.getId();
		if(objectID == null){
			upload.setCreated(now);
		}
		
		upload.setModified(now);
		
		return uploadRepository.save(upload);
		
	}

	public Upload findOne(Integer id) {
		
		return uploadRepository.findById(id);
	}
	/**
	 * We need to save both the original image and the 
	 * @param image
	 * @param path
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public void saveImage(MultipartFile image, String path) throws RuntimeException, IOException {
		try {
			
			
			
			byte[] bytes = image.getBytes();
            BufferedOutputStream buffStream = 
                    new BufferedOutputStream(new FileOutputStream(new File((path))));
            buffStream.write(bytes);
            buffStream.close();
            
//            if (!image.getOriginalFilename().equals("")) {
//            	image.transferTo(new File(saveDirectory + image.getOriginalFilename()));
//            }
            
		} catch (IOException e) {
			throw e;
		}
	}

	
	
	public Upload addItemTable(Upload upload, int id, String linkTable){
		upload.setLinkId(id);
		upload.setLinkTable(linkTable);
		return uploadRepository.save(upload);
	}
	
	
	public Upload addProfileToUpload(Profile profile, Upload upload) {
		upload.setLinkId(profile.getId());
		upload.setLinkTable("profiles");
		return uploadRepository.save(upload);
	}

	public List<Upload> findAllWithUuid(String uuid) {
		
		return uploadRepository.findByUuid(uuid);
	}

	public List<Upload> findProfileUploads(Profile owningProfile) {
		
		return uploadRepository.findByUploadOwner(owningProfile);
//		return uploadRepository.findByUploadOwnerQ(owningProfile.getId());
		
	}

	public Upload findOne(String key) {
		
		return uploadRepository.findByKeyIdentification(key);
	}
	
	public Upload getUpload(int id){
		return uploadDAO.getUpload(id);
	}

	public Upload save(Upload upload) {
		return uploadRepository.save(upload);
		
	}

	public Upload findFirstAlbumUpload(Album album) {
	
		return uploadRepository.findByAlbum(album);
		
	}

	public List<Upload> findByUuidAndDisplay(String uuid, boolean display) {
		
		return uploadRepository.findByUuidAndDisplay(uuid, display);
	}
	
	public Upload findOneByUuidAndDisplay(String uuid, boolean display){
		return uploadRepository.findOneByUuidAndDisplay(uuid, display);
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return uploadDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}
	
	public List<Upload> getUploads(com.elevysi.site.pojo.Page page){
		return uploadDAO.getUploads(page);
	}
	
	public List<Upload> findPaginatedProfileUploads(Profile owningProfile, com.elevysi.site.pojo.Page page) {
		return uploadDAO.findByUploadOwner(owningProfile, page);
		
	}
	
	public List<Upload> getAllAlbumUploads(Album album){
		return uploadDAO.findAllAbumUploads(album);
	}
	
	public List<Upload> findPaginatedAlbumUploads(Album album, com.elevysi.site.pojo.Page page) {
		return uploadDAO.findPaginatedAlbumUploads(album, page);
		
	}
	
//	ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//	BufferedImage bufferedImage = ImageIO.read(bis);
//	http://forum.spring.io/forum/spring-projects/web/37507-resizing-a-uploaded-image-file
	
	private static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		if (ret.getHeight() < targetHeight || ret.getWidth() < targetWidth) {
			higherQuality = false;
		}
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}
	
	public void setAllAlbumUploads(Album album, boolean display){
		uploadDAO.findAllAbumUploadsForUpdate(album, display);
	}
	
	public void updateUploadForDisplay(int id, boolean display){
		uploadDAO.updateUploadForDisplay(id, display);
	}
	
	public void uploadImage(MultipartFile image, String path) throws RuntimeException, IOException {
		
		try{
			byte[] bytes = image.getBytes();
            BufferedOutputStream buffStream = 
                    new BufferedOutputStream(new FileOutputStream(new File((path))));
            buffStream.write(bytes);
            buffStream.close();
            
            
            String avatarDirPath = this.avatarUploadPath+"originals"+this.ds;
			File avatarDir = new File(avatarDirPath);
			if (!avatarDir.exists()) {
				if (avatarDir.mkdirs()) {
					
					
				} else {
					
				}
			}
            
//            if (!image.getOriginalFilename().equals("")) {
//            	image.transferTo(new File(saveDirectory + image.getOriginalFilename()));
//            }
		}catch(IOException e){
			throw e;
		}

	}
}
