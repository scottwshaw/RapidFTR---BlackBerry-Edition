package com.rapidftr.controllers;

import com.rapidftr.datastore.FormStore;
import com.rapidftr.model.Child;
import com.rapidftr.screens.ManageChildScreen;
import com.rapidftr.screens.SnapshotScreen;
import com.rapidftr.screens.UiStack;
import com.rapidftr.services.ChildStoreService;
import com.rapidftr.utilities.ImageCaptureListener;

public class ManageChildController extends Controller {

	private final FormStore formStore;
	private final ChildStoreService childStoreService;

	public ManageChildController(ManageChildScreen screen, UiStack uiStack,
			FormStore formStore, ChildStoreService childStoreService) {
		super(screen, uiStack);
		this.formStore = formStore;
		this.childStoreService = childStoreService;
	}

	public void synchronizeForms() {
		dispatcher.synchronizeForms();
	}

	public void show() {
		((ManageChildScreen) screen).setForms(formStore.getForms());
		super.show();
	}
	

	public void showEditScreenForChild(Child child) {
		((ManageChildScreen) screen).setEditForms(formStore.getForms(),child);
		super.show();
	}

	public void takeSnapshotAndUpdateWithNewImage(
			ImageCaptureListener imageCaptureListener) {

		SnapshotController snapshotController = new SnapshotController(
				new SnapshotScreen(), uiStack);
		snapshotController.show();
		snapshotController.setImageListener(imageCaptureListener);
	}

	public void saveChild(Child child) {
		childStoreService.syncChildWithStore(child);
	}

	public void syncChild(Child child) {
		dispatcher.syncChild(child);
		
	}

}