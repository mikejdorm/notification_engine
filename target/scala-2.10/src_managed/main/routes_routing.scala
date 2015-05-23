// @SOURCE:/Users/michaeldorman/Desktop/notification_engine/conf/routes
// @HASH:1ef91fef78803749d07c97272a64cb5404dae2c4
// @DATE:Sat May 23 12:01:01 CDT 2015


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:10
private[this] lazy val controllers_Application_messages2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("messages"))))
        

// @LINE:11
private[this] lazy val controllers_Application_voice3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("voice"))))
        

// @LINE:12
private[this] lazy val controllers_Application_status4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("status"))))
        

// @LINE:13
private[this] lazy val controllers_Application_fallback5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("fallback"))))
        

// @LINE:14
private[this] lazy val controllers_Application_callStatus6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("callstatus"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """messages""","""controllers.Application.messages()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """voice""","""controllers.Application.voice()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """status""","""controllers.Application.status()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """fallback""","""controllers.Application.fallback()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """callstatus""","""controllers.Application.callStatus()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:10
case controllers_Application_messages2(params) => {
   call { 
        invokeHandler(controllers.Application.messages(), HandlerDef(this, "controllers.Application", "messages", Nil,"GET", """""", Routes.prefix + """messages"""))
   }
}
        

// @LINE:11
case controllers_Application_voice3(params) => {
   call { 
        invokeHandler(controllers.Application.voice(), HandlerDef(this, "controllers.Application", "voice", Nil,"GET", """""", Routes.prefix + """voice"""))
   }
}
        

// @LINE:12
case controllers_Application_status4(params) => {
   call { 
        invokeHandler(controllers.Application.status(), HandlerDef(this, "controllers.Application", "status", Nil,"POST", """""", Routes.prefix + """status"""))
   }
}
        

// @LINE:13
case controllers_Application_fallback5(params) => {
   call { 
        invokeHandler(controllers.Application.fallback(), HandlerDef(this, "controllers.Application", "fallback", Nil,"POST", """""", Routes.prefix + """fallback"""))
   }
}
        

// @LINE:14
case controllers_Application_callStatus6(params) => {
   call { 
        invokeHandler(controllers.Application.callStatus(), HandlerDef(this, "controllers.Application", "callStatus", Nil,"POST", """""", Routes.prefix + """callstatus"""))
   }
}
        
}

}
     