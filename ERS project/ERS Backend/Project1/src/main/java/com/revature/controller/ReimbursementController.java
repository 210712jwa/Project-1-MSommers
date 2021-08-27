package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dto.AddOrEditReimbursementDTO;
import com.revature.dto.MessageDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

	private ReimbursementService reimService;

	public ReimbursementController() {
		this.reimService = new ReimbursementService();
	}

	private Handler getAllReimbursementsByUser = (ctx) -> {

		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You must be logged in to do this"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");

			String userId = ctx.pathParam("userid");

			if (currentUser.getuID() == Integer.parseInt(userId)) {

				List<Reimbursement> reims = reimService.getAllReimbursementsFromUserId(userId);

				ctx.json(reims);
				ctx.status(200);

			} else {
				ctx.json(new MessageDTO("You are not a user designated to see this content"));
				ctx.status(401);
			}
		}

	};
	
	
	private Handler getAllReimbursementsByStatus = (ctx) -> {

		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You must be logged in to do this"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");


			if (currentUser.getUserRole().getuID() == 2) {
				
				String rStatus = ctx.pathParam("status");

				List<Reimbursement> reims = reimService.getAllReimbursementsByStatus(rStatus);

				ctx.json(reims);
				ctx.status(200);

			} else {
				ctx.json(new MessageDTO("You are not a user designated to see this content"));
				ctx.status(401);
			}
		}

	};
	

	private Handler getAllReimbursements = (ctx) -> {

		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You must be logged in to do this"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");

			//String userId = ctx.pathParam("userid");

			if (currentUser.getUserRole().getuID() == 2) {

				List<Reimbursement> reims = reimService.getAllReimbursements();

				ctx.json(reims);
				ctx.status(200);

			} else {
				ctx.json(new MessageDTO("You are not a user designated to see this content"));
				ctx.status(401);
			}
		}

	};
	
	
	private Handler editReimbursementByID = (ctx) -> {

		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You must be logged in to do this"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");

			//String userId = ctx.pathParam("userid");

			if (currentUser.getUserRole().getuID() == 2) {
				
				String uId = ctx.pathParam("userId");
				String reimbId = ctx.pathParam("reimbId");
				
				AddOrEditReimbursementDTO reimStatusDto = ctx.bodyAsClass(AddOrEditReimbursementDTO.class);
				
				Reimbursement editedReim = reimService.editReimById(uId, reimbId, reimStatusDto);

				ctx.json(editedReim);
				ctx.status(200);

			} else {
				ctx.json(new MessageDTO("You are not a user designated to see this content"));
				ctx.status(401);
			}
		}

	};
	
	
	
	private Handler createReimbursementByUserID = (ctx) -> {

		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You must be logged in to do this"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");

			AddOrEditReimbursementDTO reimToCreate = ctx.bodyAsClass(AddOrEditReimbursementDTO.class);
			
			String uId = ctx.pathParam("userid");

			if (currentUser.getuID() == Integer.parseInt(uId)) {

				Reimbursement createdReims = reimService.creatReimbursementById(uId, reimToCreate);

				ctx.json(createdReims);
				ctx.status(200);

			} else {
				ctx.json(new MessageDTO("You are not a user designated to see this content"));
				ctx.status(401);
			}
		}

	};
	
	

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/user/:userid/reimbursement", getAllReimbursementsByUser);
		app.get("/reimbursements", getAllReimbursements);
		app.get("/filterByStatus/:status", getAllReimbursementsByStatus);
		app.put("/user/:userId/editReimbursement/:reimbId", editReimbursementByID);
		app.post("/user/:userid/reimbursement", createReimbursementByUserID);

	}

}
