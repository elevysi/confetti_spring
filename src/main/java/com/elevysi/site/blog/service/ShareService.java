package com.elevysi.site.blog.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevysi.site.blog.dao.ShareRepository;
import com.elevysi.site.blog.entity.Share;

@Service
public class ShareService extends BasicService{
	
	@Autowired
	private ShareRepository shareRepository;

	public boolean hasAlreadyShared(Share share) {
		
//		Share savedShare = shareRepository.findSharedItem(share.getItemId(), share.getItemType(), share.getShareOwner().getId());
//		if(savedShare != null){
//			return true;
//		}
		return false;
	}

	public Share saveShare(Share share) {
		
		Date now = new Date();
		share.setCreated(now);
		share.setModified(now);
		
		return shareRepository.save(share);
		
	}
	
	public Set<Share> itemShares(Integer itemId, String itemType){
		Set<Share> itemShares = shareRepository.findByItemIdAndItemType(itemId, itemType);
		
		return itemShares;
	}
	
	public Set<Share> itemFullShares(Integer itemId, String itemType){
		Set<Share> itemShares = shareRepository.findItemShares(itemId, itemType);
		
		return itemShares;
		
	}
	
	

}
