
svn_log_file = File.open('db/data/svn_log.xml')
svn_log_hash = Hash.from_xml(svn_log_file.read)

svn_list_file = File.open('db/data/svn_list.xml')
svn_list_hash = Hash.from_xml(svn_list_file.read)

for entry in svn_list_hash['lists']['list']['entry']
  project_name = entry['name'].split('/').first
  project = Project.find_by_title(project_name)
  if  project.nil?
    project = Project.create(title: project_name)
  end
  if entry['kind'] == 'file'
    path = entry['name']
    name = path.split('/').last
    file_type = name.split('.').last
    record = project.file_records.create(path: entry['name'], size: entry['size'].to_i, name: name, file_type: file_type,
    file: 'https://raw.github.com/seanfreiburg/cs242/master/' + entry['name']+'?login=seanfreiburg&token=82db3ca0c8ad16ea8ff53317bef4756b')
    commit = entry['commit']
    record.file_versions.create(author: commit['author'], date: commit['date'], revision: commit['revision'])
    #:author, :date, :file_record_id, :message, :revision
  end
end


for log_entry in svn_log_hash['log']
  for path in  log_entry[1][1]['paths']
    for str in path[1]
      split_path= str.strip.split('/').drop(2)
      split_path = split_path.join('/')
      file_record = FileRecord.find_by_path(split_path)
      puts split_path if file_record

      file_record.file_versions.create(author: entry['author'], date: entry['date'], revision: entry['revision']) if file_record
    end
  end

end
