package com.apyx.macrotest

import com.apyx.macrotest.macros.AddTraitToObject
import com.apyx.macrotest.macros.AddAbstractClassToObject
import com.apyx.macrotest.macros.AddClassWithGenerics

object Main {
	
	def main(args:Array[String]):Unit = {
	
		println("- DONE -")
	}

}

@AddClassWithGenerics object ToModifyByAddingClassWithGenerics { }
@AddAbstractClassToObject object ToModifiyByAddingAbstractClass { }
@AddTraitToObject object ToModifiyByAddingTrait { }