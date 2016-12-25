from controller.AbstractParser import AbstractParser

class KotlinParser (AbstractParser):
    def declareImports(self):
        print("Declaring Kotlin Imports")

    def openClass(self):
        print("Openning Kotlin Class")

    def declareMembers(self):
        print("Declaring Kotlin Members")

    def declareMethods(self):
        print("Declaring Kotlin Methods")

    def closeClass(self):
        print("Closing Kotlin Class")
