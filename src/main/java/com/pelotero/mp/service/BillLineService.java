package com.pelotero.mp.service;

import com.pelotero.mp.bean.BillLine;
import com.pelotero.mp.generic.GenericService;

import java.util.Set;

/**
 * Created by User on 13/12/2017.
 */
public interface BillLineService extends GenericService<BillLine> {

    Set<BillLine> saveAll(Set<BillLine> billLines);
}
