from abc import ABC, abstractmethod

class AbstractParser(ABC):

    def __init__ (self, metadata):
        pass

    @abstractmethod
    def declareImports(self):
        pass

    @abstractmethod
    def openClass(self):
        pass

    @abstractmethod
    def declareMembers(self):
        pass

    @abstractmethod
    def declareMethods(self):
        pass

    @abstractmethod
    def closeClass(self):
        pass

    def createFile (self):
        self.declareImports ()
        self.openClass ()
        self.declareMembers ()
        self.closeClass ()
