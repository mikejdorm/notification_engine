// @SOURCE:/Users/michaeldorman/Desktop/notification_engine/conf/routes
// @HASH:1ef91fef78803749d07c97272a64cb5404dae2c4
// @DATE:Sat May 23 12:01:01 CDT 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def voice(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "voice")
}
                                                

// @LINE:13
def fallback(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "fallback")
}
                                                

// @LINE:12
def status(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "status")
}
                                                

// @LINE:14
def callStatus(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "callstatus")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:10
def messages(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "messages")
}
                                                
    
}
                          
}
                  


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def voice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.voice",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "voice"})
      }
   """
)
                        

// @LINE:13
def fallback : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.fallback",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "fallback"})
      }
   """
)
                        

// @LINE:12
def status : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.status",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "status"})
      }
   """
)
                        

// @LINE:14
def callStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.callStatus",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "callstatus"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:10
def messages : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.messages",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "messages"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def voice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.voice(), HandlerDef(this, "controllers.Application", "voice", Seq(), "GET", """""", _prefix + """voice""")
)
                      

// @LINE:13
def fallback(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.fallback(), HandlerDef(this, "controllers.Application", "fallback", Seq(), "POST", """""", _prefix + """fallback""")
)
                      

// @LINE:12
def status(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.status(), HandlerDef(this, "controllers.Application", "status", Seq(), "POST", """""", _prefix + """status""")
)
                      

// @LINE:14
def callStatus(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.callStatus(), HandlerDef(this, "controllers.Application", "callStatus", Seq(), "POST", """""", _prefix + """callstatus""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:10
def messages(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.messages(), HandlerDef(this, "controllers.Application", "messages", Seq(), "GET", """""", _prefix + """messages""")
)
                      
    
}
                          
}
        
    