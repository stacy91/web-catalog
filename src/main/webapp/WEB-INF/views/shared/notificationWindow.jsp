<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

		
		<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" 
		aria-labelledby="mySmallModalLabel" aria-hidden="true" 
		id="NotificationWindow">
		<div class="modal-dialog modal-sm">
    		<div class="modal-dialog">
   		 		<div class="modal-content">
      				<div class="modal-header">
      				
      					<c:choose>
      					<c:when test="${empty link}" >
        				<button type="button" class="close" data-dismiss="modal">
        					<span aria-hidden="true">&times;</span>
        					<span class="sr-only">Close</span>
        				</button>
        				</c:when>
        				<c:when test="${not empty link}" >
        				
        				<div  class="close">
        				<a href="${link }" >
        					<span aria-hidden="true">&times;</span>
        					<span class="sr-only">Close</span>
        				</a>
        				</div>
        				
        				</c:when>
        				</c:choose>
        				
        				<h4 class="modal-title" id="myModalLabel"><tiles:getAsString name="title"/></h4>
      				</div>
      				<div class="modal-body">
        				<tiles:getAsString name="content"/>
      				</div>
      				
      				<c:choose>
      				<c:when test="${not empty link}" >
      				<div class="modal-footer">

        				<a href="${link}"><div  class="btn btn-default" >Ok</div ></a>

      				</div>
      				</c:when>
      				<c:when test="${empty link}" >
      				<div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
      				</div>
      				</c:when>
      				</c:choose>
    			</div>
    		</div>
  		</div>
  		</div>