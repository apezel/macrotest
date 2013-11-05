package com.apyx.macrotest.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.Context

class AddTraitToObject extends StaticAnnotation {
	def macroTransform(annottees: Any*) = macro TestMacro.AddTraitToObject_impl //doesn't work
}

class AddAbstractClassToObject extends StaticAnnotation {
	def macroTransform(annottees: Any*) = macro TestMacro.AddAbstractClassToObject_impl //works
}

class AddClassWithGenerics extends StaticAnnotation {
	def macroTransform(annottees: Any*) = macro TestMacro.AddClassWithGenerics_impl //doesn't work
}

object TestMacro {
	def AddTraitToObject_impl(c:Context)(annottees:c.Expr[Any]*): c.Expr[Any] = {
		import c.universe._
	
	    def insertTree(moddef:ModuleDef):Tree = {
			
			val q"$mods object $name extends ..$ext { ..$body }" = moddef
			
			val oBody = body :+ q"trait MyTrait { val a = 0 }"
	    
			q"object $name extends ..$ext { ..$oBody }"
		}
		
	    val inputs = annottees.map(_.tree).toList
	    val (annottee, expandees) = inputs match {
			case (x:ModuleDef) :: rest => (EmptyTree, insertTree(x) +: rest)
			case x:Any => (EmptyTree, x)
	    }
	    
	    println("[INFO] Will insert trait...")
			
		return c.Expr[Any](Block(expandees, Literal(Constant(()))))
		
	}
	
	def AddAbstractClassToObject_impl(c:Context)(annottees:c.Expr[Any]*): c.Expr[Any] = {
		import c.universe._
	
	    def insertTree(moddef:ModuleDef):Tree = {
			
			val q"$mods object $name extends ..$ext { ..$body }" = moddef
			
			val oBody = body :+ q"abstract class MyAbstract { def a:Int }"
	    
			q"object $name extends ..$ext { ..$oBody }"
		}
		
	    val inputs = annottees.map(_.tree).toList
	    val (annottee, expandees) = inputs match {
			case (x:ModuleDef) :: rest => (EmptyTree, insertTree(x) +: rest)
			case x:Any => (EmptyTree, x)
	    }
	    
	    println("[INFO] Will insert abstract class...")
			
		return c.Expr[Any](Block(expandees, Literal(Constant(()))))
		
	}
	
	def AddClassWithGenerics_impl(c:Context)(annottees:c.Expr[Any]*): c.Expr[Any] = {
		import c.universe._
	
	    def insertTree(moddef:ModuleDef):Tree = {
			
			val q"$mods object $name extends ..$ext { ..$body }" = moddef
			
			val oBody = (body 
					:+ q"abstract class MyAbstract1[T] { def myDef[U](arg1:Function[T, MyAbstract1[U]]):MyAbstract1[U] }"
					:+ q"abstract class MyAbstract2[T] { def myDef[U](arg1:Function[T, MyAbstract1[U]]):MyAbstract2[U] }")
	    
			q"object $name extends ..$ext { ..$oBody }"
		}
		
	    val inputs = annottees.map(_.tree).toList
	    val (annottee, expandees) = inputs match {
			case (x:ModuleDef) :: rest => (EmptyTree, insertTree(x) +: rest)
			case x:Any => (EmptyTree, x)
	    }
	    
	    println("[INFO] Will insert class with generics...")
			
		return c.Expr[Any](Block(expandees, Literal(Constant(()))))
		
	}
	
}