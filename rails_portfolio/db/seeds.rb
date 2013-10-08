# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create

svn_log_file = File.open('db/data/svn_log.xml')
svn_log_hash = Hash.from_xml(svn_log_file.read)

svn_list_file = File.open('db/data/svn_list.xml')
svn_list_hash = Hash.from_xml(svn_list_file.read)

for entry in svn_list_hash['lists']['list']['entry']
  project_name = entry['name'].split('/').first
  project = Project.find_by_title(project_name)
  if  project.nil?
    project = Project.create(title: project_name)
    #attr_accessible :date, :summary, :title, :version
  end
  if entry['kind'] == 'file'
    path = entry['name']
    name = path.split('/').last
    file_type = name.split('.').last
    record = project.file_records.create(path: entry['name'], size: entry['size'].to_i, name: name, file_type: file_type,
    file: 'https://github.com/seanfreiburg/cs242/blob/master/' + entry['name'])
    commit = entry['commit']
    record.file_versions.create(author: commit['author'], date: commit['date'], revision: commit['revision'])
    #:author, :date, :file_record_id, :message, :revision
  end
end

#
#for entry in svn_log_hash['log']['logentry']
#  for path in entry['paths']
#    split_path= path.split('/')
#    split_path = split_path[2..path.size]
#    split_path = split_path.join('/') if split_path
#    #file_record = FileRecord.find_by_path(path)
#    #puts path if file_record
#    puts split_path
#
#    #file_record.file_versions.create(author: entry['author'], date: entry['date'], revision: entry['revision']) if file_record
#  end
#end
