/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;


import AssetManagement.AssetManagement.entities.DetailAsset;
import AssetManagement.AssetManagement.repository.DetailAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class DetailAssetServices {
    
    @Autowired
    private DetailAssetRepository detailAssetRepository;
    
    public Iterable<DetailAsset> findAll() {
        return detailAssetRepository.findAll();
    }
}
