file = open('KotlinSupportLibrary_AppCompatActivity.kt')

print("|{:30s}|{:20s}|{:40s}|{:10s}|".format("Function", "Description", "Arguments", "Return"))
print('|' + '-'*30 + '|' + '-'*20 + '|' + '-'*40 + '|' + '-'*10)

for line in file:
    if 'fun ' in line:
        function = line.partition("AppCompatActivity.")[2].partition("(")[0]
        import re

        try:
            arguments = re.findall(r'\((.*?)\)', line)[0]
        except IndexError:
            arguments = ''
        try:
            rvalue = re.findall(r'(?<=\):)[^}]*(?={)', line)[0]
        except IndexError:
            rvalue = ''

        print("|{:30s}|{:20s}|{:40s}|{:10s}|".format(function, "description", arguments, rvalue))
