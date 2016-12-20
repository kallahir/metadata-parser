
import os, sys, re

# CONSTANTS --------------------------------------------------------------------
COLUMN_SIZE = "|{:50s}|{:30s}|{:100s}|{:25s}|"
DIRECTORY_PATH = sys.argv[1] + '/'
OUTPUT = "output.txt"
OUTPUT_EDITOR = open(OUTPUT, 'a')

# FUNCTIONS --------------------------------------------------------------------
def printHeader () :
    writeOutput('\n\n###' + fNAme)
    writeOutput(COLUMN_SIZE.format("Function", "Description", "Arguments", "Return"))
    writeOutput('|' + '-'*50 + '|' + '-'*30 + '|' + '-'*100 + '|' + '-'*25)

def getFName (line) :
    try:
        rvalue = '```' + re.findall(r'(?<=fun )[^}]*(?=\()', line)[0].partition('.')[2] + '```'
    except IndexError:
        rvalue = 'script error'
    return rvalue

def getFArguments (line) :
    try:
        arguments = '```' + re.findall(r'\((.*?)\)', line)[0] + '```'
    except IndexError:
        arguments = 'no args'
    return arguments

def getFReturnType (line):
    try:
        rvalue = re.findall(r'(?<=\):)[^}]*(?={)', line)[0]
    except IndexError:
        rvalue = ' Unit '
    return '```'+ rvalue + '```'

def writeOutput (line):
    OUTPUT_EDITOR.write(line + '\n')

def clearFile():
    with open(OUTPUT, "w"):
        pass

def parseFile (fName):
    for line in open (fName):
        if 'fun ' in line:
            content = COLUMN_SIZE.format(getFName(line), "No description available yet.", getFArguments(line), getFReturnType(line))
            writeOutput(content)

# MAIN FUNCTION ----------------------------------------------------------------
clearFile()
for fNAme in os.listdir (DIRECTORY_PATH):
    printHeader()
    filePath = DIRECTORY_PATH + fNAme
    parseFile(filePath)
print('Finished parse successfully. Output dumped on: ' + OUTPUT)
